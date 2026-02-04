## F1 Bet App

### Clone and run
1. git clone git@github.com:guilhermestella/f1-bet.git
2. cd f1-bet/
3. docker build -t f1-bet .
4. docker run -p 8080:8080 f1-bet

### Users
Static available users (username and password):
1. admin / admin
2. foo / foo
3. bar / bar

### Use App
Use [swagger](http://localhost:8080/swagger-ui/index.html#/) to 
interact and consume the endpoints.

Inside Swagger, don't forget to click on Authorize 
to authenticate your user.

### Dependencies
No infrastructure is required as the app stores data in memory. 
