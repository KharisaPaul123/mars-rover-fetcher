# Mars Rover Image Fetcher

## Description
This project uses the NASA API to download images taken by Mars rovers on specified dates, then stores them locally. It was developed in Java to fulfill the given exercise requirements.

**Task Overview**

Using the NASA API, this project calls the Mars Rover API, selects a picture taken on a given day, and downloads and stores each image locally. The application reads dates from a .txt file and fetches the corresponding images.

**Acceptance Criteria**

Dates to Fetch Images: The application will fetch images for the following dates, as specified in the provided text file:

02/27/17

June 2, 2018

Jul-13-2016

April 31, 2018

**Code Requirements:**

The project is written in Java.

The project should be able to be built and run locally after submission.

Include relevant documentation (README.md, etc.) in the repository.

**Bonus Features:**
Bonus 1: Unit tests, static analysis, performance tests, or any other best practices.

Bonus 2: Application should run in Docker.

### Features:
- Fetches images from NASA's Mars Rover API.
- Supports multiple date formats and handles errors gracefully.
- Saves downloaded images to the local filesystem.
- Provides feedback and error messages for invalid dates, API issues, and failed image downloads.

## 🛠️ Installation

To get started with the Mars Rover Image Fetcher, follow these steps:

### Prerequisites:

- **Java 8+** (Ensure Java is installed on your system)
- **Maven** (for managing dependencies)
- **NASA API Key** (You need to create an API key from NASA’s website)
- **Docker** (optional, for containerized version).
- **JUnit** (for running unit tests, optional).



## 🛠️ How to Set Up the Project

### Steps to Run the Project:

1. Clone this repository to your local machine:
   ```bash
   git clone https://github.com/yourusername/MarsRoverImageFetcher.git

2. Navigate into the project directory
   ```bash
   cd MarsRoverImageFetcher
3. Open the src folder and find the MarsRoverImageFetcher.java file. Replace "YOUR_NASA_API_KEY" with your actual NASA API key in the code.
4. Build the project: If you're using Maven, you can run:
   ```bash
   mvn clean install
5. Run the project:
   ```bash
   java -cp target/mars-rover-image-downloader.jar com.marsrover.app.MarsRoverImageFetcher





    

### 🧩 Dependencies

The following dependencies are used in this project:

Jackson for JSON parsing: Used to parse the API response.

Maven for project management and dependency handling.
Dependencies in pom.xml:
<dependencies>
    <!-- Jackson for JSON parsing -->
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.12.4</version>
    </dependency>

    <!-- Maven dependencies -->
    <dependency>
        <groupId>org.apache.maven</groupId>
        <artifactId>maven-plugin-api</artifactId>
        <version>3.8.1</version>
    </dependency>
</dependencies>


### ❗ Known Limitations

Invalid Dates Handling: If an invalid date like "April 31, 2018" is provided, the program skips to the next valid date (e.g., May 1, 2018). A more detailed error message or fallback logic could be implemented to log such errors more clearly.

Unit Test Issues: Under crunch time, some unit tests failed due to insufficient error handling and edge cases. These errors need to be addressed to ensure smooth test execution in future iterations.

Docker: The Docker image might require adjustments depending on your system configuration. Ensure Docker is properly set up and that the volume mount points are correctly specified.



## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

### MIT License

The MIT License (MIT)

Copyright (c) 2025 Kharisa Paul

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

