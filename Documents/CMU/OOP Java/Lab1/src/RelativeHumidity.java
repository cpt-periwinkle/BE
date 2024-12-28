	import java.util.Scanner;

	// Your name: Shlok Kalekar
	// Your Andrew id: skalekar

	// class RelativeHumidity
	// Prompts the user for the temperature and dewpoint in Fahrenheit.
	// Converts them to Celsius.
	// Computes the saturation for each.
	// Computes and displays the relative humidity.
	public class RelativeHumidity {

		public static void main(String[] args) {

					// Don't change this code
			RelativeHumidity relativeHumidity = new RelativeHumidity();
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter the temperature in Fahrenheit: ");
			double temperature = scanner.nextDouble();
			System.out.print("Enter the dewpoint in Fahrenheit: ");
			double dewpoint = scanner.nextDouble();
			System.out.println("You entered:");
			System.out.println("temperature: " + temperature);
			System.out.println("dewpoint: " + dewpoint);
			
					// Convert to Celsius
					// Your code goes here
					double temperature_celsius, dewpoint_celsius;
					temperature_celsius = relativeHumidity.convertTemp(temperature);
					System.out.println( "Temperature in Celsius: " + temperature_celsius);
					dewpoint_celsius = relativeHumidity.convertTemp(dewpoint);
					System.out.println( "Dewpoint in Celsius: " + dewpoint_celsius);
			
					// Compute the saturations sT (temperature) and sD (dewpoint)
					// Your code goes here
					double saturation_temp, saturation_dew;
					saturation_temp = relativeHumidity.calcSaturation(temperature_celsius);
					System.out.println( "Saturation Value for Temperature in Celsius: " + saturation_temp);
					saturation_dew = relativeHumidity.calcSaturation(dewpoint_celsius);
					System.out.println( "Saturation Value for Dewpoint in Celsius: " + saturation_dew);

					// Compute the relative humidity
					// Your code goes here
					double rel_humidity;
					rel_humidity = relativeHumidity.calculateRelativeHumidity(saturation_temp, saturation_dew);
					System.out.println( "Relative Humidity: " + rel_humidity + " %");

					scanner.close();
		}

		public double calculateRelativeHumidity(double sat_temp, double sat_dew) {
			return 100 * (sat_temp / sat_dew);
		}

		public double convertTemp ( double temp_f ) {
			return (5.0/9.0) * (temp_f - 32) ;
		}

		public double calcSaturation ( double temp ) {
			return Math.exp((17.625 * temp)/(243.04 + temp));
		}
		
	}
