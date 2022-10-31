# Test task

Small test task for start-up project

## About project

It's basic REST spring boot application with H2 database.

## Technologies i have used

- Java (Programming Language)
- Spring Boot (Application Platform)
- Spring Data JPA (Data persistence)
- Spring Data validation (Data validation)
- SpringDoc (API documentation for spring based applications)
- Modelmapper (Helps to map objects)
- H2 (Database)
- Liquibase (Tool for managing and executing database changes)
- JUnit, with Spring Testing (Unit & Integration Testing)
- Logger 
- lombok

## Project description

Project has only 1 end-point - /api/users/{userId} which gets user by its id
The @Operation & @ApiResponse annotations allow us to add some description to the API documentation with the Open API 3.0 specification.
I use dto to display user data, using dto to get and display data is good practice in rest applications.
```java
    @Operation(summary = "Get user by its id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found user",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserDto.class))}),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)})
    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(value = ID) Long userId){
       UserDto userResponse = userService.findById(userId);

        log.info(USER_GET_LOG, userId);

        return ResponseEntity.ok(userResponse);
    }
```

In UserService when want to get user by his id we need also calculate his age by his birth date.
I wrote some addition code for calculation:
```java
@Override
    public UserDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        UserDto userResponse = modelMapper.map(user, UserDto.class);

        userResponse.setAge(getDiffYears(user.getDateOfBirth(), new Date()));
        return userResponse;
    }

    public static int getDiffYears(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(YEAR) - a.get(YEAR);
        if (a.get(MONTH) > b.get(MONTH) ||
                (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
            diff--;
        }
        return diff;
    }

    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }
```

## Tests
First test is test for getting user by his id,
We check that if, after request of "/api/users/1" url, the status 200(OK) is returned to us user with correct fields.
```java
@Test
    public void getUserById() throws Exception {
        log.info(TEST_GET_USER_STARTED);
        mvc.perform(MockMvcRequestBuilders.get(TEST_GET_USER_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("student1 first name"))
                .andExpect(jsonPath("$.lastName").value("student1 last name"))
                .andExpect(jsonPath("$.age").value(19));
        log.info(TEST_GET_USER_FINISHED);
    }
```

Second test is test for get UserNotFoundException when we are trying to get user which does not exist in our database,
We check that if, after request of "/api/users/4" url, the status 404(NOT_FOUND) and returned to us message "Could not find user with id 4".
```java
@Test
    public void getUserByIdAndExpectUserNotFoundException() throws Exception {
        log.info(TEST_GET_USER_EXCEPTION_STARTED);

        mvc.perform(MockMvcRequestBuilders.get(TEST_GET_USER_EXCEPTION_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UserNotFoundException))
                .andExpect(result -> assertEquals("Could not find user with id 4", result.getResolvedException().getMessage()));
        log.info(TEST_GET_USER_EXCEPTION_FINISHED);
    }
```

## Here is the Swagger UI page for my API.
We can open Swagger UI page by url - /swagger-ui.html
![image](https://user-images.githubusercontent.com/44270738/199084219-02e28b5a-7b1b-4f20-95ab-a2b82d3cf26d.png)

## How to run project
```
$ git clone https://github.com/MachinaIlucha/TestTask.git
$ cd TestTask
$ mvnw clean install
$ mvnw test
$ mvnw spring-boot:run
```
