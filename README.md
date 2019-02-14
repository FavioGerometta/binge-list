# Coding Challenge

Hello and welcome to this coding challenge. In this challenge, you are tasked with building the _My Binge List_ app. When finished, the app will allow the users to search for movies based on a genre and allow them to add movies to their favorites to binge watch later.

So let's get to the details, shall we?

## How it works?

We will be providing you with tasks to implement during this coding challenge (which you will find in the Github Issues section). When you are ready, you need to send us a pull request. One of our engineers will review your pull request, give you feedback comments (sometimes you may be required to do some changes), and if all goes well you'll be able to merge the pull request. And then we will give you another task to implement. This will go on until we decide it is ready to move to the next step beyond the coding challenge. 

Please keep in mind:

* We will be evaluating every pull request you send based on the rubric that we provided in the `rubric.md` file here.
* You can expect to have 2-4 tasks depending on the responses. We can also stop the coding challenge after your first pull-request.
* We will not be reviewing your story/task branches, only your pull-request.
* Please, only implement what is asked in the current active task. Nothing less, nothing more. And of course, pay extra attention to providing tests.
* Please DO NOT publish your solutions on a public facing profile. These coding challenges are intended for internal XING interview processes.
* We will delete this repository once the coding challenge or the final interview process is done.

## The "App"

We provided you with a [`design/artboards_advanced.pdf`](design/artboards_advanced.pdf) file to give you an idea of the finished experience. BTW, keep in mind, the design is created by a fellow engineer ;)

We also provided you with a [skeleton app](client/MyBingeList) . Feel free to change files, folder structures, etc. In the app, we also provided you with the basic UI and the design assets you might need. 

## The "Backend"

We also provided you a mock API server to build the app against. You will find a [`server\docker-compose.yml`](server\docker-compose.yml) file that has the server available. In order to run the server locally on your machine follow the next steps:

* Please (if not already) install [Docker for Mac](https://docs.docker.com/docker-for-mac/install/)
* Once you clone this repo on your local machine, go to the server folder and run `docker-compose up` in your terminal window. This will download the necessary containers and bring the service up.
* The documentation for the service can be found at the [Github repo for this service](https://github.com/keremk/movie-service). The service will be available at [`http://localhost:4000`](http://localhost:4000) when it is running.
* When you are done, you can run `docker-compose down` to stop all the containers.

## Next steps

Now if you are ready, go ahead, clone this repo on your local machine. Take a look at your first task in the issues tab of this repository and get started!
