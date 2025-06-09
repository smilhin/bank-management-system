# Bank Management System

This application simulates a multi-panel user interface for a bank system using Java Swing. It includes basic authentication (no encryption or persistence) and panel navigation, but it does **not** connect to a real backend or database server.

---

## Project Structure

### Packages

- **window** – Main package:
  - `Launcher` – Main class
  - `BankJFrame` – Initializes main frame, contains all event listeners
  - `LoginJPanel` – Login screen

  - **coworker** (sub-package for bank staff):
    - `CoworkerJPanel` – Main dashboard
    - `CoworkerAccountsJPanel` - View all accounts
    - `CoworkerBarJPanel` - Bar class for Sidebar and Topbar
    - `CoworkerTransactionsJPanel` – View all transactions

  - **customer** (sub-package for users/customers):
    - `CustomerJPanel` – Main dashboard
    - `CustomerBarJPanel` - Bar class for Sidebar and Topbar
    - `CustomerCreditJPanel` - Take a credit
    - `CustomerTransactionsJPanel` – View personal transactions

- **database** – Contains:
  - Core database class
  - `User` class and subclasses
  - `Transaction` record

---

Note: This is a personal learning project to demonstrate my Java programming skills. It is **not intended for real-world use**.

---

## Installation

### Windows Executable

1. Go to the `dist/` folder.
2. Download `BankApp.zip`.
3. Unzip the file.
4. Inside the `BankApp/` folder, run `BankApp.exe`.

> Make sure the `app/` and `runtime/` folders remain next to the `.exe` file, as they are required for the application to run.
