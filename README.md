# Furhat-Weather-ChatBot
A social chatbot developed for furhat robot, which can give the weather details of any city around the world.
# Furhat
Before going to the actual project and task at hand, let's first understand what is a social robot. Social robots are a combination of conversational AI and physical robots. This factor makes them very much different from a normal chatbot and avatars. The problem with chatbots or avatars is that they can be called social but they are not physical. Conversely for manufacturing robots, they are physical but not social. The reason for choosing Furhat for this project is, because it is a social robot.
Furhat is a social robot with facial expression like a human and advanced conversational Artificial Intelligence capabilities.  
There are many special features in Furhat. One of those special features is "Back Projection" is a feature that allows the user to choose the face of Furhat. The face of Furhat is not made of any complex control mechanism to deliver human like expression, but is a simple projector screen on which different faces can be projected. Furhat also allows the users to design their own avatars i.e. skin colour, placement of eyebrows, the amount of makeup, male or female characteristics, size of eyes or lips etc. Furthermore, user can also choose whichever voice they like. There are many options available for both male and female voices. In addition, the robot can also move its head in the direction of the user, nod its head, and have a perfect lip sync with respect to 40 plus different languages.  
The customizability of Furhat leads to the immense possibilities of different personalities and use cases. Among many use cases two popular use cases of Furhat are

1. Pre-Screening Medical Robot by Merck

2. Airport assistants built by Deutsche Bahn Systel 
# Weather Agent Development
For furhat skill development kotlin programming language is used. Depending upon the type of skill one want to implement, the files that need to be changed differs. In this project, three files were modified: the main.kt, interaction.kt, and build.gradle. The main.kt file contains the main method that starts the furhat robot.Additionally, it is the file in which all the intents are defined. For this project two intents are being used: GetWeather, and GetDetails.The GetWeather intent captures the city and the date that the user is interested in, and the GetDetails intent captures whether the user want to know about humidity, pressure, and wind speed.
The interaction.kt file, as the name suggests this file is used to design the dialogues between the user and the agent. Furhat robotics has already defined basic intents for responses such as Yes or No.
