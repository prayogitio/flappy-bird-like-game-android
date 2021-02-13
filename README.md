## Flappy Square Game

The objective is to exercise creating flappybird-like game on Android.  
We have a square object with jump behavior as that of flappybird.  

Here is the screenshot of the game:  
![Screenshot_2021-02-13-17-08-21-963_com example flappypig](https://user-images.githubusercontent.com/33726233/107853676-e941be80-6e49-11eb-8bc6-ebd94945bab8.jpg)


### Explanation of the source code
We only have activity which is `MainActivity`.  
We construct `GameView` object and play the game.

There are 2 classes that are orchestrated by the `GameView`:
1. **Pig**  
The square object that will jump pass the lumbers.
2. **Lumber**  
The obstacle for the `Pig`. If the `Pig` collided with the lumber, then the game is over.
