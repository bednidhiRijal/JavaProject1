/*
Name: Bednidhi Rijal (Ben)
Date: 27-04-2020
Time:8:13 pm

Problem to be solved: 
Need to process Marks[] to compute maximum, minimum, range, mode, median, grade of marks once the grade 
is determined, and count the grade distribution.

*/
import java.util.Arrays;

public class ProcessMarks{
	public static void main(String[] args){
		int[] gradeBoundary = {85, 75, 65, 50, 0};
		char[] grade = {'A', 'B', 'C', 'D', 'F'};
		int[] marks = Marks.getMarks(); 	//call a getMarks method to get array of marks
		
		/** call a method to find the minimum mark in the set of marks*/
		int minimumMark = min(marks);
		
		/** call a method to find the maximum mark in the set of marks*/
		int maximumMark = max(marks);
		
		/** call a method to find the range of maximum and minimum mark in the set of marks */
		int highestAndLowestDifference = range(marks);
		
		/** call a method to find the mean of the set of Marks */
		float meanMark = mean(marks);
		/** call a method to find the median of the set of Marks */
		float medianMark = median(marks);
		
		/** call a method to find the mode of the set of marks */
		int modeMark = mode(marks);
		
		/** call a method to find the grades of each mark */
		char[] gradeArray = grades(marks, grade, gradeBoundary);
		
		/** call a method to find the grade distribution */
		int[] gradeDistribution = gradeDistn(gradeArray, grade);
		
		/*display the result i.e. maximum mark, minimum mark, range, median mark, mode, average mark, grads, grade distribution*/
		System.out.printf("**********************************************************\n\n");
		System.out.println("Maximum Mark : " + maximumMark);
		System.out.println("Minimum Mark : " + minimumMark);
		System.out.println("Range of maximum and Minimum mark : " + highestAndLowestDifference);
		System.out.printf("Average mark : %.3f\n", meanMark);
		System.out.printf("Median mark : %.2f\n", medianMark);
		System.out.println("Mode mark : "+ modeMark);
		System.out.println("\nGrades:");
		for(int i = 0; i < marks.length; i++){		//grade is stored in array so use loop to dispaly grade stored in gradeArray
			if(i%30==0)
				System.out.println();
			System.out.printf("%c ",gradeArray[i]);
		}
		System.out.println("\n\ngrade Distribution:\n");
		for(int i = 0;i<gradeDistribution.length; i++){		//grade distribution is stored in array so use loop to display it
			System.out.println(grade[i]+": "+gradeDistribution[i]);
		}
		System.out.printf("\n**********************************************************");
	}
	
	
	/**This method find and return maximum mark. 
	Here highestMark is initialized with the inital mark and is compared with 
	every marks in an array. During comparision once highestMark is lower, its value is updated with the value to which it is compared
	with to ensure it always holds higest mark. This process continues until we reach to the end of array*/
	public static int max(int[] cMarks){
		int highestMark = cMarks[0]; 		//initialize with a first element of an array cMarks
		for(int i = 1; i < cMarks.length; i++){
			if(highestMark < cMarks[i])					//check whether highestMark hold maximum mark and if not we update it with new highest mark  
				highestMark = cMarks[i];
		}
		return highestMark;	
	}
	
	
	/**Find and return minimum mark*/
	public static int min(int[] cMarks){
		int lowestMark = cMarks[0];				//initialize with a first element of an array cMarks
		for(int i = 1; i < cMarks.length; i++){
			if(lowestMark > cMarks[i])			//check whether lowestMark hold manimum mark and if not we update it with new lowest mark
				lowestMark = cMarks[i];
		}
		return lowestMark;
	}
	
	/**Find difference between highest and lowest mark and return difference*/
	public static int range(int[] cMarks){
		int lowestMark, highestMark;
		lowestMark = min(cMarks);		//call a method to get minimum marks of the set of marks
		highestMark = max(cMarks);		//call a method to get maximum marks of the set of marks
		return (highestMark - lowestMark);	//return range of the highest and lowest mark
	}
	
	/**Find and return average of the set of marks*/
	public static float mean(int[] cMarks){
		int sum = 0;
		float average = 0;
		for(int i=0; i<cMarks.length; i++){
			sum += cMarks[i]; 			// add all the element of an array cMarks to find the total sum
		}
		average = sum/(float)cMarks.length;		//compute mean by dividing total sum of all marks by total number of mark.
		return average;
	}
	
	/**Find and return median value of the set of marks*/
	public static float median(int[] cMarks){ 
		float medianMark;
		int[] sortedMarks = sortingMarksCopy(cMarks);		//call a method to get a sorted copy of orignal marks in ascending order
		if(sortedMarks.length/2 == 0)		//if number of marks is even, median is the average of two center element
			medianMark = (float)(sortedMarks[sortedMarks.length/2] + sortedMarks[sortedMarks.length/2 + 1])/2;
		else{								//if number of marks is odd, median is in the center
			medianMark = sortedMarks[(sortedMarks.length/2) + 1];
		}
		return medianMark;
	}
	
	
	/**Find and return mode of the set of marks*/
	public static int mode(int[] cMarks){
		int[] sortedMarks = sortingMarksCopy(cMarks);		//call a method to get a sorted copy of original marks in ascending order
		int markRepetionCount = 0,markRepetionCountFinal=0, modeMark=0;		//variable declaration
		for( int i = 0, j = 0; i<sortedMarks.length; i++){
			if(sortedMarks[j] == sortedMarks[i]){		//count repetition of same marks
				markRepetionCount++;
			}
			else{
				if(markRepetionCountFinal < markRepetionCount){		//check whether markRepetionCountFinal hold the maximum count of occurance of same marks, if not update it with new highest count
					markRepetionCountFinal = markRepetionCount;
					modeMark = sortedMarks[i-1];			//store marks whose occurance is highest in the given set of marks 
				}
				j=i;
				markRepetionCount = 1;		//if marks change, reset markRepetionCount
			}
		}
		return modeMark;
	}
	
	/**Find and return array of grades corresponding to each mark in the array of marks*/
	public static char[] grades(int[] cMarks, char[] grade, int[] gradeBoundary){
		char[] gradeArray = new char[cMarks.length];		//create an array to hold corresponding grades of each marks
		/**Check boundary condition for each mark to determine the grade and storing grade in gradeArray*/
		for(int i = 0;i < cMarks.length; i++){				
			for(int j = 0;j < grade.length; j++){
				if(cMarks[i] >= gradeBoundary[j]){
					gradeArray[i] = grade[j];
					break;
				}
			}
		}
		return gradeArray;
	}
	
	/**Accept an array of grades assigned for marks[] and return distribution_of_grades[]*/
	public static int[] gradeDistn(char[] gradeArray, char[] grade){
		int[] gradeDistribution = new int[5];
		/** count the repetition of same grade and store this count in gradeArray for each distinct grade*/
		for(int i=0;i<gradeArray.length; i++){
			for(int j=0; j<grade.length;j++){
				if(gradeArray[i] == grade[j]){		
					gradeDistribution[j] += 1;
					break;
				}
			}
		}
		return gradeDistribution;
	}
	/**Accept an marks[], make a copy of marks[] and sort it in ascending order */
	public static int[] sortingMarksCopy(int[] cMarks){
		int[] copyMarks = new int[cMarks.length];		// create an array to hold the copy of marks
		for(int i = 0; i < cMarks.length; i++){
			copyMarks[i] = cMarks[i];				//copy marks array to copyMarks i.e. creating copy of original marks
		}
		Arrays.sort(copyMarks);				//sort an array in ascending order that hold the copy of original marks using ARRAYS sort function.
		System.out.printf("\n\n");

		return copyMarks;
	}
}
