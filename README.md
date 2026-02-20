# MA MZ Tool

MA MZ Tool is a data extraction and analysis utility for ManagerZone.

It parses manually copied squad data from ManagerZone pages and transforms it into structured player objects for further processing and analysis.

The tool supports multiple parsing strategies depending on the available input.

---

## 🎯 Purpose

ManagerZone does not provide a complete public API for full player profiles.

MA MZ Tool was created to:

- Extract detailed player information from Squad pages
- Convert unstructured text or HTML into structured domain objects
- Allow offline analysis and testing
- Provide deterministic parsing independent of external APIs

---

## 🧠 Parsing Modes

### 1️⃣ Full Profile Parsing (Legacy Mode – More Detailed) - Obsolete

Originally, the tool parsed the **HTML page source** of:

Squad → Player Profiles

This mode allowed extraction of more detailed player attributes because the full profile page contained richer information.

However, this depends on the stability of the HTML structure and does not work anymore due page layout changes.

---

### 2️⃣ Squad Summary Parsing (Current Working Mode – Less Detailed)

The currently working mode parses the **visible text content** from the Squad Summary page.

Usage:

1. Open Squad Summary in ManagerZone
2. Select all visible text (Ctrl+A)
3. Copy
4. Paste into the tool
5. Run parsing

This mode extracts fewer attributes because the summary page itself contains less information than the full player profile page.

Advantages:
- More stable
- Independent of HTML source structure
- Works purely on visible text

Limitation:
- Reduced data richness compared to full profile parsing

---

### 3️⃣ Automatic Test Data Fallback

If:

- No text is provided
- Input is empty
- Parsing fails

The tool automatically generates internal test data.

This guarantees:
- No runtime failure due to missing input
- Stable UI behavior
- Continued development without live game data

---

## 📂 Test Data

The project includes:
test/test.txt

This file contains archived test data based on an older version of the Squad – Player Profiles page source.

It is used for:

- Parser validation
- Regression testing
- Development without requiring live ManagerZone data


