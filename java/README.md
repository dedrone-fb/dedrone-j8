# Background

Some games like Dungeons and Dragons use what are known as polyhedral dice. These dice have various numbers of sides: 4, 6, 8, 10, 12, 20, and 100. The 6-sided dice are the same sort of dice used in games like Monopoly, backgammon, etc., and produce random numbers from 1 to 6. The other dice work similarly: a 4-sided die produces a random number from 1 to 4; a 12-sided die produces a random number from 1 to 12. Each number is equally likely to come up for a given die.

Sometimes, you roll just one die; sometimes you roll a few and add their results together. For example, in Monopoly, you roll two 6-sided dice and add the results.

## IDiceRoller Interface

The IDiceRoller interface describes the API of a class to simulate rolling one or more polyhedral die as described above. It is reproduced here:

## BrokenDiceRoller class

The class BrokenDiceRoller (which implements the IDiceRoller interface) is broken, like its name implies. That is, while it does implement the interface, it does not do it according to the “contract” (i.e., the required behavior) described in the javadoc for the interface, and in more than one way.

Please do NOT directly open the BrokenDiceRoller.java class in your ide to figure out what is wrong.

## Tasks

- Determine in what specific ways the BrokenDiceRoller class is deficient. (There are multiple things wrong with it).
- Write your own class which implements the IDiceRoller interface that works correctly with some sort of validation that could be run in a CICD pipeline. You can select the name of your class and the implementation algorithm, but do not change the interface in any way.
