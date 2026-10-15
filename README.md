# Compiler Design

An educational project exploring compiler design concepts through a Java-based lexical analyzer and preliminary syntax-analysis structures.

## Overview

This project was developed as an exercise in compiler design. Its main component is a lexical analyzer (lexer) that scans a sample program and identifies tokens such as keywords, identifiers, numeric literals, operators, strings, characters, comments, and whitespace.

The repository also contains preliminary structures related to grammar representation and FIRST/FOLLOW set calculations.

**Project status: Incomplete / Educational**

This project is not a complete compiler. The lexical analyzer is implemented but has not been comprehensively validated, and the syntax-analysis component remains unfinished.

## Features

- Lexical analysis of a sample source program
- Recognition of language keywords and identifiers
- Handling of numeric literals, including decimal and hexadecimal forms
- Recognition of operators and punctuation
- Handling of string and character literals
- Recognition of comments and whitespace
- Token representation using a dedicated `Token` class
- Preliminary grammar and FIRST/FOLLOW-related structures
- Sample input and output files
- Accompanying project documentation in PDF and DOCX formats

These features describe the current implementation and do not imply complete or standards-compliant support for every possible input.

## Project Structure

```text
.
├── src/
│   ├── Main.java
│   ├── LexicalAnalyzer.java
│   ├── Token.java
│   ├── SyntaxAnalyzer.java
│   ├── Variable.java
│   └── FirstClass.java
├── data/
│   ├── input/
│   └── output/
├── documentation/
│   ├── Documentation.pdf
│   └── Documentation.docx
├── LICENSE
└── README.md
```

## Requirements

- Java Development Kit (JDK)
- A terminal or Java-compatible IDE

## Build and Run

From the repository root, compile the Java source files:

```bash
javac -d out src/*.java
```

Run the main class:

```bash
java -cp out Main
```

The current implementation uses a sample source program defined inside `LexicalAnalyzer.java` and prints recognized tokens to the console.

**Note:** File input is not enabled in the default execution path. The current output routine also contains a hard-coded Windows-specific path, so saving output files may not work as expected on other systems.

## Current Limitations

- The syntax analyzer is only a preliminary structure; parsing is not implemented.
- Grammar definition and the `Variable` class's FIRST/FOLLOW calculation methods are unfinished.
- The separate `FirstClass` implementation is experimental and should not be considered a validated FIRST-set calculator.
- The lexer has not been comprehensively tested against malformed input and edge cases.
- Input handling and output paths are not portable.
- Token storage and position handling may have limitations for overlapping or conflicting token detections.
- There is no automated test suite or build-system configuration.

## Documentation and Examples

The `data/` directory contains sample inputs and previously generated outputs. The `documentation/` directory contains the project's accompanying PDF and DOCX documents.

These materials are provided for learning and reference. They may not represent a fully tested or reproducible build of every component.

## Scope

This repository preserves an educational implementation of selected compiler-design concepts. It is intended for code exploration, study, and reference rather than production use.

The project is incomplete and may require additional implementation, testing, and refactoring before it can be used as a functional compiler.

## License

See the [LICENSE](LICENSE) file for the applicable license terms.

## Acknowledgment

This repository documents an educational project and its implementation at the time of publication. It is shared as a reference for learning about compiler construction in Java.

## Authors

This project was developed by:

- **Seyed Hossein Hosseini DolatAbadi**
- **Reza Alaei**
