# Quotes Rest API
Rest API allowing users to view, create, update, replace and delete quotes.

# Instructions for starting the application
### 1. Create and start a PostgreSQL database:
```
Name: postgres
Port: 5432
Username: postgres
Password: password
```
### 2. Download and install dependencies in Maven
### 3. Run Spring Boot Application



# Endpoints Documentation

### HOST: http://localhost:8080/api/quotes/

## [GET]

### List of all quotes [GET]

+ Response 200 (application/json)

            {
                "_embedded": {
                    "quoteList": [
                        {
                            "id": 5,
                            "author": "Benjamin Franklin",
                            "contents": [
                                "Tell me and I forget. Teach me and I remember. Involve me and I learn.",
                                "There never was a good war or a bad peace.",
                                "When you’re good to others, you’re best to yourself."
                            ],
                            "_links": {
                                "self": {
                                    "href": "http://localhost:8080/api/quotes/5"
                                }
                            }
                        },
                        {
                            "id": 7,
                            "author": "Thomas A. Edison",
                            "contents": [
                                "Many of life's failures are people who did not realize how close they were to success when they gave up.",
                                "We often miss opportunity because it's dressed in overalls and looks like work."
                            ],
                            "_links": {
                                "self": {
                                    "href": "http://localhost:8080/api/quotes/7"
                                }
                            }
                        }
                    ]
                },
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/api/quotes"
                    },
                    "quotesByAuthor": {
                        "href": "http://localhost:8080/api/quotes?author={author}",
                        "templated": true
                    }
                }
            }
            
### List of all quotes by author's id [GET]

+ Response 200 (application/json)
+ Location: /{id}

            {
                "id": 5,
                "author": "Benjamin Franklin",
                "contents": [
                    "Tell me and I forget. Teach me and I remember. Involve me and I learn.",
                    "There never was a good war or a bad peace.",
                    "When you’re good to others, you’re best to yourself."
                ],
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/api/quotes/5"
                    }
                }
            }
            
### List of all quotes by author's name [GET]

+ Response 200 (application/json)
+ Query Params: ?author=Benjamin Franklin

            {
                "id": 5,
                "author": "Benjamin Franklin",
                "contents": [
                    "Tell me and I forget. Teach me and I remember. Involve me and I learn.",
                    "There never was a good war or a bad peace.",
                    "When you’re good to others, you’re best to yourself."
                ],
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/api/quotes/5"
                    }
                }
            }

## [POST]

### Create new quotes of one author [POST]

+ Request (application/json)

        {
           "author": "Benjamin Franklin",
           "contents": [
                          "Tell me and I forget. Teach me and I remember. Involve me and I learn.", 
                          "There never was a good war or a bad peace.", 
                          "When you’re good to others, you’re best to yourself."
                        ]
        }

+ Response 201 (application/json)

### Create new quote of specific author by author's id [POST]

+ Request (text plain value)
+ Location: /{quoteId}/quotes

```
Tell me and I forget. Teach me and I remember. Involve me and I learn.
```


## [PATCH]
### Update author's quotes or name by author's id [PATCH]
+ Request (application/json)
+ Location: /{id}
            {
                "contents": ["We often miss opportunity because it's dressed in overalls and looks like work"]
            }


+ Response 200 (application/json)

## [PUT]
### Replace all author's name and quotes by author's id [PUT]
+ Request (application/json)
+ Location: /{id}
            {
                "author": "Thomas A. Edison",
                "contents": ["Many of life's failures are people who did not realize how close they were to success when they gave up."]
            }

+ Response 200 (application/json)

## [DELETE]
### Delete all author's quotes by author's id [DELETE]
+ Response 200 (application/json)
+ Location: /{id}
