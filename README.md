# Orderbook
 An order book for traders. A CRUD web application with a Java + MySQL back end, and a React front end. Allows order addition and modification, and tracks the history of each order. Automatic matching is performed, and a ticker feed is available alongside a simple trade history graph.  
 
**Implementation Plan**: https://docs.google.com/document/d/1vcAyzN833MIv-DnNW1zf6JEDJ-kobmpZeC6L4VoxDtc/edit?usp=sharing  
**Release Notes**: https://docs.google.com/document/d/1MTCaAIHR3eXnT8rF7gdrEyQCuSQkqRv9oDbb0X_PIPo/edit?usp=sharing

# Release Notes (27.08.2020)

### Order Book - v1.0

1.**Overview**

The purpose of this web application is to manage stock trading. The users of the system can create new buy/sell orders, update or cancel existing orders, commit trades, follow stock market trends using a ticker or observe changes in trade prices over time using a graph. Server data is kept in a MySQL database, which is dynamically updated as users interact with the web application.

2.**Functionality of Order Book v1.0**

- Create buy/sell orders
- Update/cancel existing orders
- Commit trades
- Stock ticker
- Graph with trades
- MySQL database connectivity

3. **Implementation Details**
  - Backend Tools
    - Java with Spring Boot used for backend server implementation
    - JUnit used for testing
    - MySQL database used for storing the data
    - JPA used for managing the relationship with the database
    - NetBeans was used as main IDE
  - Frontend Tools
    - React with JavaScript
    - VSCode was used as main IDE
  - Version Control
    - GitHub desktop

Further details regarding implementing and running the web application would be available in the Implementation Plan file.

4. **Supporting Files**
  - Implementation Plan: Specifies how to install the application and run it
  - Rollback Plan: Specifies how to roll back to a previous stable release in case of delivery failure
  - Change log: Has information regarding new additions to each release. Not available for Order Book v1.0 (current release) because it is the first release.
  - Wireframes: Act as a map for the web page
  - Entity Relationship Diagram: Visual representation of the structure of the MySQL database
  - Class Diagram: Visual representation of the relationships between classes in the backend
  - Flow Chart: Visual representation of the flow of operations of the web application
