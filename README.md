# Enterprise Computing with Java

**Course:** CP630 - Enterprise Computing  
**Student:** Adeniyi Ridwan Adetunji  
**ID:** 245852450  
**Institution:** Wilfrid Laurier University

## Overview

This repository contains all lab exercises and assignments completed for the Enterprise Computing course, covering modern enterprise technologies, distributed systems, big data processing, and AI/ML integration.

## Repository Structure
```
Enterprise-Computing-Java/
├── lab1/          # Java EE Fundamentals & Eclipse Setup
├── lab2/          # Servlets, JSP & Web Applications
├── lab3/          # EJB, JPA & Database Integration
├── lab4/          # RESTful Web Services & JAX-RS
├── lab5/          # Big Data: Cassandra, Hadoop, Spark & Transformers
├── a1/            # Assignment 1: Java EE Application
├── a2/            # Assignment 2: Database & ORM
├── a3/            # Assignment 3: RESTful Services
├── a4/            # Assignment 4: Microservices Architecture
└── a5/            # Assignment 5: Big Data & ML Models
```

## Technologies & Tools

### Core Technologies
- **Java EE 8** - Enterprise application development
- **Jakarta EE** - Modern enterprise Java standards
- **WildFly 26** - Application server
- **Maven** - Build automation and dependency management

### Databases
- **MySQL 8** - Relational database
- **Apache Cassandra 3.11** - NoSQL distributed database
- **JPA/Hibernate** - Object-relational mapping

### Big Data & Distributed Computing
- **Apache Hadoop 2.7** - Distributed storage and processing
- **HDFS** - Hadoop Distributed File System
- **MapReduce** - Parallel data processing framework
- **Apache Spark 2.4** - Fast distributed computing engine
- **Scala** - Spark programming

### AI/ML & NLP
- **Python 3.8+** - ML development
- **Hugging Face Transformers** - Pre-trained models
- **PyTorch** - Deep learning framework
- **DistilBERT** - Transformer model for NLP
- **Flask** - Python microservices

### DevOps & Tools
- **Docker** - Containerization
- **Git/GitHub** - Version control
- **Eclipse IDE** - Java development
- **Postman** - API testing

## Lab Summaries

### Lab 1: Java EE Setup & Fundamentals
- Eclipse IDE configuration
- WildFly server setup
- First Java EE application deployment
- Servlet basics

### Lab 2: Web Application Development
- Servlets & JSP pages
- Session management
- Form handling & validation
- MVC architecture patterns

### Lab 3: Enterprise JavaBeans & Persistence
- Stateless and Stateful EJBs
- JPA entity management
- JPQL queries
- Database transactions
- MySQL integration

### Lab 4: RESTful Web Services
- JAX-RS implementation
- JSON/XML data handling
- REST API design principles
- Service testing with Postman
- CORS configuration

### Lab 5: Big Data & Machine Learning
- **Apache Cassandra**: NoSQL database operations, CQL programming
- **Hadoop HDFS**: Distributed file system operations, CLI commands
- **MapReduce**: Word count, K-means clustering algorithms
- **Apache Spark**: RDD operations, Spark SQL, MLlib, GraphX
- **Transformers**: Dataset exploration, tokenization, fine-tuning DistilBERT for emotion classification (92.85% accuracy)
- **Microservices**: Flask-based AI model serving

## Assignment Highlights

### A1: Enterprise Web Application
Built a complete Java EE web application with servlets, JSP, and session management.

### A2: Database-Driven Application
Implemented JPA entities, CRUD operations, and complex database queries with MySQL.

### A3: RESTful API Development
Created a comprehensive REST API with full CRUD operations and proper HTTP methods.

### A4: Microservices Architecture
Designed and implemented a microservices-based system with service discovery and inter-service communication.

### A5: Big Data & AI Integration (100/100)
- **Statistics Computing**: Hadoop MapReduce for statistical analysis (count, min, max, mean, std)
- **Spark ML**: Linear regression model conversion to JSON
- **Transformer Fine-tuning**: Fine-tuned DistilBERT on emotion dataset (6 classes)
  - Achieved 92.85% validation accuracy
  - Deployed as Flask microservice
  - Supports real-time emotion prediction via REST API
- **Model Integration**: HDFS client for model persistence, Cassandra for model storage

## Key Learning Outcomes

✅ Enterprise application architecture and design patterns  
✅ Distributed systems and scalability concepts  
✅ RESTful API design and implementation  
✅ Big data processing with Hadoop and Spark  
✅ NoSQL database design and operations  
✅ Machine learning model fine-tuning and deployment  
✅ Microservices architecture and integration  
✅ DevOps practices and containerization  

## Running the Projects

### Prerequisites
- JDK 8 or higher
- WildFly 26 Application Server
- MySQL 8
- Apache Hadoop 2.7.1
- Apache Spark 2.4.7
- Python 3.8+ (for ML components)
- Maven 3.6+

### Setup Instructions

**Java EE Applications (Labs 1-4, A1-A4):**
```bash
# Build the project
mvn clean package

# Deploy to WildFly
cp target/app.war $WILDFLY_HOME/standalone/deployments/

# Access application
http://localhost:8080/app
```

**Big Data Projects (Lab 5, A5):**
```bash
# Start Hadoop
start-all.cmd

# Run MapReduce job
hadoop jar target/stats-hd.jar /input /output

# Start Spark
spark-shell --master spark://localhost:7077

# Run Python ML service
python app.py
```

## Contact

**Name:** Adeniyi Ridwan Adetunji  
**Email:** aden2450@mylaurier.ca  
**GitHub:** [ovxncdev](https://github.com/ovxncdev)

## Acknowledgments

- Course Instructor: Dr. Rudafshani, Masoomeh
- Wilfrid Laurier University, Department of Computer Science
- CP630 - Enterprise Computing Course Materials

## License

This repository is for educational purposes only. Code examples and implementations are based on course materials from CP630 at Wilfrid Laurier University.

---

*Last Updated: December 2025*
