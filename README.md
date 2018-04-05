# Maven Integration Test with Docker

[![Build Status](https://travis-ci.org/ghusta/maven-integration-test-with-docker.svg?branch=jdk-9)](https://travis-ci.org/ghusta/maven-integration-test-with-docker)

## Overview

The goal is to launch **integration tests** via Maven, using Docker to start and stop a container. 

The plugin [docker-maven-plugin](https://github.com/fabric8io/docker-maven-plugin) can help us to do that. See [documentation](https://dmp.fabric8.io/).

## Usage

> mvn clean verify
