const sourceText = document.getElementById("sourceText");
const translateBtn = document.getElementById("translateBtn");
const resultBox = document.getElementById("resultBox");

const AUTH_HEADER = "Basic " + btoa("user:1234");
const apiUrls = [
  "http://localhost:8080/translate",
  "http://localhost:8080/motarjimApp-1.0-SNAPSHOT/api/translate"
];

async function getSelectedTextFromPage() {
  try {
    const [tab] = await chrome.tabs.query({ active: true, currentWindow: true });
    if (!tab || !tab.id) {
      return;
    }

    const [result] = await chrome.scripting.executeScript({
      target: { tabId: tab.id },
      func: () => window.getSelection().toString().trim()
    });

    if (result && result.result) {
      sourceText.value = result.result;
    }
  } catch (error) {
    console.error("Could not fetch selected text:", error);
  }
}

async function sendTranslateRequest(text) {
  let lastError = null;

  for (const url of apiUrls) {
    try {
      const response = await fetch(url, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization": AUTH_HEADER
        },
        body: JSON.stringify({ text })
      });

      if (!response.ok) {
        const msg = await response.text();
        throw new Error(`HTTP ${response.status}: ${msg}`);
      }

      return await response.text();
    } catch (error) {
      lastError = error;
    }
  }

  throw lastError || new Error("Translation request failed.");
}

translateBtn.addEventListener("click", async () => {
  const text = sourceText.value.trim();
  if (!text) {
    resultBox.textContent = "Please enter or select text first.";
    return;
  }

  resultBox.textContent = "Translating...";
  translateBtn.disabled = true;

  try {
    const translated = await sendTranslateRequest(text);
    resultBox.textContent = translated || "No translation returned.";
  } catch (error) {
    resultBox.textContent = `Error: ${error.message}`;
  } finally {
    translateBtn.disabled = false;
  }
});

getSelectedTextFromPage();
