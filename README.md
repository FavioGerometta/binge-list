# Android Coding Challenge

Hello and welcome to your coding challenge. In this challenge, you build the _My Binge List_ app. When finished, the app allows users to search for movies based on a genre and add movies to their favorites to binge-watch later.

## Preconfigured with mocks
*The app is set up with mocked data that responds to Action and Drama categories only

## What you need to use live data

* [**Docker**](https://docs.docker.com/install/) installed and running. Create a user account if you don't have one yet.

## The Backend

Go to the `server/` folder and run `docker-compose up` in a terminal window. This command downloads the needed container files and brings the service up.

The service returns JSON data via [`http://localhost:4000`](http://localhost:4000). Find the documentation in the [Github repo for this service](https://github.com/keremk/movie-service).

Run `docker-compose down` to stop the containers again.
