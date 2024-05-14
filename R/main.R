# function for plotting data
plotresult <- function(file, start = 1) {   
  data <- read.csv(file, header=TRUE, sep=" ")
  data <- data[start:nrow(data),]
  plot(data, type = 'l')
}

getMeanAndConfidence <- function(eqVal = 94, iterations = 5) {
  means <- c()
  confidenceIntervals <- c()
  for (i in c(1:iterations)) {
    system("java -classpath ../out/production/lab5 sorters.Labb AES 128 out.txt 600")
    parserData <- read.csv("out.txt", header=TRUE, sep=" ")
    
    extractedVal <- parserData[eqVal:nrow(parserData),]
    meanVal <- mean(extractedVal$Time)
    means[i] <- meanVal
  }
  print("Mean value: ")
  finalMean <- mean(means)
  confidence <- confidenceInterval(means)
  print(confidence)
  print(finalMean)
  plotresult("out.txt")
}

# getMeanAndConfidence(10)
#system("/home/karl/.jdks/openjdk-21.0.2/bin/java -classpath /home/karl/Courses/edaa35/labbar/lab5/out/production/lab5 Labb /home/karl/Courses/edaa35/labbar/lab5InR/input1.txt out.txt 600")
 # plot to screen
#pdf("out.pdf")
#plotresult("out.txt") # plot to pdf file
#dev.off()
