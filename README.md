# Motarjim App - LLM-Powered RESTful Translation Service

Mini Project 2 implementation for translating text (e.g., English) to Moroccan Darija using a Jakarta REST backend and client applications.

## Project Status

### Implemented
- Jakarta REST backend (`TranslatorResource`) with `POST` translation endpoint
- Basic authentication (Jakarta Security) with in-memory identity store
- CORS support for browser-based client calls
- Chrome Extension (Manifest V3) with popup UI:
  - auto-read selected text from current tab
  - translate button calling backend
  - result display
- UML diagrams (Use Case, Sequence, Class, Deployment)

### Planned / In Progress
- Real LLM provider integration (current service is mock translation)
- Python client app
- PHP client app
- React Native mobile client app

---

## Tech Stack

- Java + Jakarta EE (JAX-RS, Security, CDI)
- Maven (WAR packaging)
- Chrome Extension (Manifest V3, HTML/CSS/JS)
- PlantUML (`.puml`) for architecture diagrams

---

## Project Structure

```text
motarjimApp/
  src/main/java/com/example/motarjimapp/
    config/
    model/
    resource/
    security/
    service/
  chrome-extension/
    manifest.json
    popup.html
    popup.css
    popup.js
    background.js
    icons/
  docs/uml/
    use-case-diagram.puml
    sequence-diagram-translate-flow.puml
    class-diagram.puml
    deployment-diagram.puml
```

---

## Backend Setup and Run

## 1) Build

```bash
mvn package
```

## 2) Deploy

Deploy `target/motarjimApp-1.0-SNAPSHOT.war` to your Jakarta-compatible server (e.g., WildFly/Payara/Tomcat with Jakarta support).

## 3) Base URL

Depending on server context root:
- `http://localhost:8080/motarjimApp-1.0-SNAPSHOT`
- or `http://localhost:8080/motarjimApp`

API path:
- `/api/translate`

Example full URL:
- `http://localhost:8080/motarjimApp-1.0-SNAPSHOT/api/translate`

---

## API Reference

### Translate Endpoint

- **Method:** `POST`
- **URL:** `/api/translate`
- **Auth:** Basic Auth
- **Content-Type:** `application/json`

### Credentials (current demo)

- Username: `user`
- Password: `1234`

### Request Body

```json
{
  "text": "Hello, how are you?"
}
```

### Example cURL

```bash
curl -X POST "http://localhost:8080/motarjimApp-1.0-SNAPSHOT/api/translate" \
  -H "Content-Type: application/json" \
  -u user:1234 \
  -d "{\"text\":\"Hello\"}"
```

### Example Response (current mock)

```text
Hello Translated Text
```

---

## Chrome Extension Setup

Extension location: `chrome-extension/`

## 1) Load in Chrome

1. Open `chrome://extensions`
2. Enable **Developer mode**
3. Click **Load unpacked**
4. Select folder: `chrome-extension`

## 2) Use

1. Select text on any webpage
2. Click extension icon
3. Popup auto-fills selected text
4. Click **Translate**
5. View result in result box

## 3) Customize Extension Icon

Place PNG files in `chrome-extension/icons/`:
- `icon16.png`
- `icon32.png`
- `icon48.png`
- `icon128.png`

Then reload the extension in `chrome://extensions`.

---

## UML Diagrams

Located in `docs/uml/`:

- `use-case-diagram.puml`
- `sequence-diagram-translate-flow.puml`
- `class-diagram.puml`
- `deployment-diagram.puml`

Render with PlantUML tools (VS Code extension / IntelliJ plugin / online renderer).

---

## Testing

Use Postman, cURL, or Thunder Client:

- Success case with valid credentials
- Unauthorized case with wrong/missing credentials
- Invalid input (empty text)

---

## Next Steps

- Replace mock translation with real Gemini/free LLM API call
- Add Python client
- Add PHP client
- Add React Native mobile app
- Record 5-minute demo video

---

## Author

- Motarjim App project (Mini Project 2)

