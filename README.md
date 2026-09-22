# Profile API

This Spring Boot API shares profile details, favorite activities, and a list of interests.

## Start the application

With Java 17 or later installed, run:

```bash
./mvnw spring-boot:run
```

The server starts at `http://localhost:2030`.

## Try the API

In Postman, choose **GET**, paste one of the URLs below, and select **Send**. The same URLs also work in a web browser.
These requests do not need authentication or a request body.

| Request                                        | URL                                                                               |
|------------------------------------------------|-----------------------------------------------------------------------------------|
| Show the profile                               | `http://localhost:2030/api/v1/welcome`                                            |
| List programming languages                     | `http://localhost:2030/api/v1/items`                                              |
| Get the first language                         | `http://localhost:2030/api/v1/item/0/`                                            |
| Search languages for “java”                    | `http://localhost:2030/api/v1/search?value=java`                                  |
| List sports                                    | `http://localhost:2030/api/v1/filter?category=sports`                             |
| Add an interest                                | `http://localhost:2030/api/v1/add-interest?value=Reading`                         |
| Update an interest                             | `http://localhost:2030/api/v1/update-interest?value=Reading&updatedValue=Writing` |
| Delete an interest                             | `http://localhost:2030/api/v1/delete-interest?value=Writing`                      |
| View totals                                    | `http://localhost:2030/api/v1/statistics`                                         |
| Get a random programming and fitness challenge | `http://localhost:2030/api/v1/challenge`                                          |

You can change the values in these examples. Language indexes start at `0`, and the filter category can be `programming`
or `sports`. Changes to interests last only until the application restarts.
