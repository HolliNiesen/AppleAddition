# Mathtastic Games Apple Addition

This game was created to encourage young individuals to embrace the complex world of mathematics.  Players are able to toggle images and choose the game difficulty.  Players who have an account are able to track their history of “tricky questions” to keep track of their progress.

## Getting Started

In the projectDocs directory, you will find a setup.sql document that will create a “mathtastic” database and enter some information.  You will receive the questions, images, and two test users.  One user will have a history of “tricky questions”, while the other will not.

If you do not already have a user with a password created on MySQL, you will need to create one.
The database.properties file will need to be updated with your username and password.

## Next Steps

When the application is launched, you should be greeted with this:

![index.jsp](/projectDocs/screenshots/index.png)

Players who do not wish to use an account can go directly to the Apple Addition game.

The starting page for the game should look like this: 

![appleAddition.jsp](/projectDocs/screenshots/appleAddition.png)

After each question, players are shown the correct answer:

![appleAdditionAnswer.jsp](/projectDocs/screenshots/appleAdditionAnswer.png)

At the end of the game, players who answered one or more questions wrong will see the questions they missed along with the number of times they attempted to answer correctly:

![appleAdditionResults.jsp](/projectDocs/screenshots/resultsTrickyQuestions.png)

Players who got through the whole game without answering a single question wrong should see:

![appleAdditionResults.jsp](/projectDocs/screenshots/resultsNoTrickyQuestions.png)

Players who wish to create an account should go to the Sign Up page:

![signUp.jsp](/projectDocs/screenshots/signUp.png)

When the user successfully creates an account, they should see:

![signUpSuccess.jsp](/projectDocs/screenshots/signUpSuccess.png)

Once an account is created, the user can use the Sign In page:

![signIn.jsp](/projectDocs/screenshots/signIn.png)

Once signed in, the user can go to their account page to update their information:

![myAccount.jsp](/projectDocs/screenshots/myAccount.png)

From there the user can view their “tricky question” history:

![trickyQuestions.jsp](/projectDocs/screenshots/trickyQuestions.png)

![trickyQuestions.jsp](/projectDocs/screenshots/noTrickyQuestions.png)
