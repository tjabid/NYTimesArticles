# NY Times Most Viewed Articles
Displaying list of most viewed articles of NY Times fetched from Rest webservice

## Design Pattern:
MVVM (Model View View Model)

## Application Flow:
One activity based app with following fragments for 3 screens:

### TitleFragment:
- Logo of NY Times for branding
- Displayed for 1.5 seconds
- ![Screenshot_title](https://user-images.githubusercontent.com/47480605/98184207-3ea13200-1f23-11eb-9079-e898c5227d84.jpg)

### ArticlesFragment
- List of most viewed articles fetched from webservice for various duration
- Filter option to change duration
- Navigation to detail screen on clicking item
- Retry button displayed with shimmer
- ![Screenshot_articles](https://user-images.githubusercontent.com/47480605/98184204-3cd76e80-1f23-11eb-8ab9-0a5e801f7658.jpg)

### DetailFragment
- Displaying article selected from previous view
- ![Screenshot_detail](https://user-images.githubusercontent.com/47480605/98184206-3e089b80-1f23-11eb-998e-383bdc054bb8.jpg)


## Webservice
```
http://api.nytimes.com/svc/mostpopular/v2/mostviewed/allsection/{period}.json?api- key=sample-key
```
`period` is 1, 7, or 30


## Building
Open the project in Android studio. Press run or debug to run on a connected device or an emulator.

## Testing
The project uses local unit tests that run on your computer. On Mac machine with a connected device or an emulator, to run both of them and generate a coverage report, you can run:
```gradlew fullCoverageReport```

## Libraries:
- Jetpack components
- Navigation for fragments
- Viewmodel with livedata
- Retrofit with Gson for web services
- Glide for image loading
- Mockito for unit testing
