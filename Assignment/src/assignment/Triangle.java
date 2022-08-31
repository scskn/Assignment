package assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Triangle
{
    public static void main(String[] args) throws IOException
    { 
    	int[][] input1 = {{1}, {8, 4}, {2, 6, 9}, {8, 5, 9, 3}};
    	int[][] input2 = readInputFromFile("input2.txt");

    	System.out.println("max sum for input1: "+getMaxSum(getNonPrimePaths(input1)));
    	System.out.println("max sum for input2: "+getMaxSum(getNonPrimePaths(input2)));
    }
    

    private static void findAllPaths(int x, int y, int input[][], List<List<Integer>> paths, List<Integer> path) //finds all non-prime paths
	{	
    	if (isPrime(input[x][y])) return;
    	
    	path.add(input[x][y]);
    	
        if (x == input.length -1)
        {
            paths.add(path);
            return;
        }
        
        findAllPaths(x+1, y, input, paths, new ArrayList<>(path));
        findAllPaths(x+1, y+1, input, paths, new ArrayList<>(path));
	}
    
    private static List<List<Integer>> getNonPrimePaths(int[][] input) //returns all non-prime paths
    {
        List<List<Integer>> paths = new ArrayList<>();
        
        findAllPaths(0, 0, input, paths, new ArrayList<>());
        
        return paths;
    }
    
    private static int getMaxSum(List<List<Integer>> paths) //returns max sum
    {
    	List<Integer> sumList = new ArrayList<Integer>();
    	 
    	for(int i = 0; i < paths.size(); i++)
    	{
    		sumList.add(paths.get(i).stream().mapToInt(Integer::intValue).sum());
    	}
    	
    	return Collections.max(sumList);
    }
    
    private static int[][] readInputFromFile(String fname) throws IOException //reads input from text file
	{
		String fileName = fname;

		List<String> lines = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(fileName)))
		{
			String line;
			while ((line = reader.readLine()) != null) {lines.add(line);}
		}
		catch (IOException e) {e.printStackTrace();}

		String[] linesArray = lines.toArray(new String[0]);

		int inputLength = linesArray.length;
		int[][] input = new int[inputLength][inputLength];
		
		for (int i = 0; i < inputLength; i++)
		{
			for (int j = 0; j < i + 1; j++)
			{
				input[i][j] = Integer.parseInt(linesArray[i].split(" ")[j]);
			}
		}
		return input;
	}
    
	private static boolean isPrime(int num) //returns 'true' if number is prime
	{
		if (num <= 1) {return false;}

		for (int i = 2; i * i <= num; i++)
		{
			if ((num % i) == 0) {return false;}
		}
		return true;
	}
}