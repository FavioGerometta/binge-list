# Android Coding Challenge

Hello and welcome to your coding challenge. In this challenge, you build the _My Binge List_ app. When finished, the app allows users to search for movies based on a genre and add movies to their favorites to binge-watch later.

## What you need

* A computer running a recent version of **MacOS** with at least **2 GB disk space** available. Docker is quite big.
* [**Docker for Mac**](https://docs.docker.com/docker-for-mac/install/) installed and running. Create a user account if you don't have one yet.

## How it works

We provide you with tasks to implement during this coding challenge. When you are ready, you need to send us a pull request. One of our engineers reviews it and gives feedback.  Sometimes you may be required to do a set of changes. If all goes well, you'll be able to merge the pull request.

Please keep in mind:

* We evaluate the pull request you send based on the [`quality_checklist.md`](quality_checklist.md) that we provided.
* We do not review any story/task branches, only your pull-request.
* Pay extra attention to providing tests.
* We delete this repository once the final interview process is over.
* Please **DO NOT publish** your solutions on a publicly. These coding challenges are intended for internal XING interview process only.

## The App

We provided you with a [`artboards_advanced.pdf`](design/artboards_advanced.pdf) file to give you an idea of the final experience.

We also provided you with a [skeleton app](client/MyBingeList). Feel free to change files or folder structure. We also added basic UI and design assets you might need.

## The Backend

We also provided you a mock API server to build the app on top. You find a [`server/docker-compose.yml`](server/docker-compose.yml) file that is used to provision the server. To run the server on your machine follow the next steps:

1. Clone this repo to your local machine
2. Go to the `server/` folder and run `docker-compose up` in a terminal window. This command downloads the necessary container and brings the service up.

The service returns JSON data via [`http://localhost:4000`](http://localhost:4000). Find the documentation in the [Github repo for this service](https://github.com/keremk/movie-service).

Run `docker-compose down` to stop the containers again.