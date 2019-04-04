# Your tasks

## Task 1

Your first task consists of validating the user input in the search field. To do it, you'll need the list of valid genres, they can be retrieved from the [client/MyBingeList/app/src/main/res/values/arrays.xml](client/MyBingeList/app/src/main/res/values/arrays.xml) file.

### Acceptance Criteria

* The user can only search for one genre at a time. If they type two or more, only the first one is used.  Keep in mind that there are genre names of that consist of two words (e.g. _Science Fiction_)
* The validation is done after the user hits the search button or enter in the keyboard.
* If the entry is not a valid genre, show a `Toast` with an error message and clear the text field.

----

## Task 2

In this task, you will be retrieving the genres from the downstream service (instead of reading from a hard-coded file like in the previous task) and implement showing the query results returned from the server.

### Acceptance Criteria

* The genre list is cached in memory, i.e. each initiated search does not require a trip to the server to get the list.
* All valid genre requests will be sent to the server to retrieve results. Page through the results with a maximum page limit of 20 results.
* The search results are shown in a thumbnail view as shown in the screenshot in the provided [artboards_advanced.pdf](design/artboards_advanced.pdf) file. There are 2 columns and the results are rendered in the left-to-right-down-left order. At this point, please do not yet implement the bookmark button. (That will be coming soon)
* Calculate the [median](https://en.wikipedia.org/wiki/Median) rating of the returned results and if a movie's rating is above or equal to the median, display a star as shown in the screenshot.
* As the user scrolls, you will receive more results and your median will change. Adjust the visibility of the star on the newly calculated median rating.

----

## Task 3

In this task, you will be implementing adding/removing movies as favorites and a new favorites screen that shows users a list of all their favorites.

### Acceptance Criteria

* Each movie cell has a button with a bookmark icon at the top left corner. If a user taps this button, then they add the movie to their favorites and the icon changes as illustrated in the art-board. And if they tap it again, they remove it from their favorites.
* There is a button (same bookmark icon style, as shown in the screenshot in the provided [artboards_advanced.pdf](design/artboards_advanced.pdf) file), at the top right of the screen. If a user taps this button, then they navigate to their favorites.
* The favorites screen shows all the movies they favored. In order to go back to the search screen, they tap the "back" button on the left. When they go back the search state is preserved. I.e. if they were looking for war movies, the same movies are shown.
* While in the favorites screen, if they remove the favorite, then it still remains on the screen (in case the user changes their mind) but the bookmark icon changes its state back to non-bookmarked. If the user navigates out of the screen and then back into the screen the non-bookmarked movie is not shown anymore.
* The favorite list is kept in memory. I.e. if the app is restarted, favorites are not persisted.