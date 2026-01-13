<div align="center">

<img src="ClickUp4Java.png" width="200" alt="ClickUp4Java logo"/>

<h1 align="center">
  <span style="color:#7B68EE;">Clic</span><span style="color:#FF4D9D;">kUp</span><span style="color:#FF8A00;">4</span><span style="color:#E76F00;">Ja</span><span style="color:#4A90E2;">va</span>
</h1>

**A friendly and productive way to use Java + ClickUp.**  

ClickUp4Java is a Java library that connects your code, tests and automation  
directly with ClickUp tasks — allowing developers and QA to update status,  
add comments and track execution without leaving the code.

![License](https://img.shields.io/github/license/LucasAlexandreBez/ClickUp4Java)
![Maven Central](https://img.shields.io/maven-central/v/com.github.lucasalexandrebez/clickup4java)
![Downloads](https://img.shields.io/github/downloads/LucasAlexandreBez/ClickUp4Java/total)
![Visitors](https://komarev.com/ghpvc/?username=LucasAlexandreBez&repo=ClickUp4Java&color=blueviolet)
![Java](https://img.shields.io/badge/Java-11%2B-orange)

---

### Open Defects / Closed Defects
![Issues](https://img.shields.io/github/issues/LucasAlexandreBez/ClickUp4Java)
![Closed Issues](https://img.shields.io/github/issues-closed/LucasAlexandreBez/ClickUp4Java)

---

## 📋Contributing

This project is open-source, but all changes to the main branch
require review and approval by the maintainers to ensure API stability.

---

[Why ClickUp4Java?](#-why-clickup4java) •
[Who is this for?](#-who-is-this-for) •
[Features](#-features) •
[Installation](#-installation) •
[Quick Example](#-quick-example) •
[Documentation](#-documentation) •
[Star History](#-star-history) •
[Contributors](#-contributors)

</div>

---

## 🕵️‍♂️ Why ClickUp4Java?

Modern software projects rely on multiple tools for tracking tasks, tests,
bugs, and progress. Despite that, they all suffer from the same problem:

**C O M M U N I C A T I O N**

During development and testing, it’s common to forget to update task statuses,
add comments, or even create new tasks when new requirements arise.

For Product Owners and Managers, this results in outdated boards and unclear
status updates during meetings.

ClickUp4Java bridges this gap by keeping **task tracking alive and connected
to the code itself**, ensuring that execution, comments and status updates
happen naturally as part of development and testing.

---

## 🤔 Who is this for?

- Java Developers
- QA Engineers (Manual & Automation)
- Test Automation Engineers
- Product Owners who want real-time visibility
- Teams using ClickUp as their task management tool

---

## ✨ Features

- Create and update ClickUp tasks via Java
- Add comments directly from code or automated tests
- Update task status during execution
- Attach logs and files to tasks *(TODO)*
- Designed to support parallel test execution *(TODO)*

---

## ⚙️ Requirements

- Java 11+
- ClickUp API Token
- Maven or Gradle

---

## 📦 Installation

### Maven
```xml
<dependency>
    <groupId>com.github.lucasalexandrebez</groupId>
    <artifactId>clickup4java</artifactId>
    <version>LATEST_VERSION</version>
</dependency>
```

### Gradle
```gradle
implementation "com.github.lucasalexandrebez:clickup4java:LATEST_VERSION"
```

Or download the JAR file and add it manually to your project.

---

## 🚀 Quick Example

```java
CreateTaskAttachments taskAttachments = new CreateTaskAttachments();
CreateTaskAttachmentResponse resp = taskAttachments.    
      callSyncCreateTaskAttachmentAPI(
        "YOUR_TASK_ID",
        "PATH_TO_FILE", 
        "CLICKUP_TOKEN",
        "CUSTOM_TASK_ID" // If you are not using custom task id, then let Optional.empty()
      );
```

---

📚 Full documentation available in the Wiki:
https://github.com/LucasAlexandreBez/ClickUp4Java/wiki

---

## ⭐ Star History

[![Star History Chart](https://api.star-history.com/svg?repos=LucasAlexandreBez/ClickUp4Java&type=Date)](
https://star-history.com/#LucasAlexandreBez/ClickUp4Java&Date
)

---

## 🧠 Contributors

![Contributors](https://contrib.rocks/image?repo=LucasAlexandreBez/ClickUp4Java)

---

## 📄 License

This project is licensed under the MIT License.  
See the [LICENSE](LICENSE) file for details.
