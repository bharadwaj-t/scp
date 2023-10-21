# SCP
A Simple Chat Program Backend (...Just not yet)

## Status
Project is in its infancy, and currently Implements the following protocols:
- [RFC 863](https://www.rfc-editor.org/rfc/rfc863)

## Building
You need the following:
1. Maven
2. Java 21 SDK

Rest is simple, its a regular spring boot application: `mvn spring-boot:run`

## Development
The course of this project is to implement a functional chat-backend to support `Client-Client` and `Client-Server`
communication; essentially DMs and Group chat respectively.

For the development itself, I am heavily dependent on Spring Boot/Framework to manage the application lifecycle and
also Vert.x too for the simplicity of writing non-blocking I/O.