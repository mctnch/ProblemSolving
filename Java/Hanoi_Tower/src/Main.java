import java.util.Scanner;

public class Main {
	static char[] positions = { 'L', 'M', 'R' };

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		System.out.print("Input number of plates : ");
		int n = sc.nextInt();
		if (n == 0) {
			return;
		}

		// Plate setting
		Plate[] plates = new Plate[n];

		// Rod setting
		Rod Rod_L = new Rod('L', n);
		Rod Rod_M = new Rod('M', n);
		Rod Rod_R = new Rod('R', n);

		for (int i = 0; i < plates.length; i++) {
			plates[i] = new Plate(i + 1, 'L');
		}

		plates[plates.length - 1].target = 'R';

		// Store All Plate in Left Rod
		RefreshAndPrint(Rod_L, Rod_M, Rod_R, plates);

		// count move
		int count = 0;
		count = move(Rod_L, Rod_M, Rod_R, plates, n - 1, count);
		System.out.println("Minimum number of moves: " + count);

	}

	public static int move(Rod Rod_L, Rod Rod_M, Rod Rod_R, Plate[] plates, int index, int count) {
		if (index > 0) {
			// set upper split target position
			for (char pos : positions) {
				if (pos != plates[index].target && pos != plates[index - 1].now) {
					plates[index - 1].target = pos;
				}
			}
			// move upper plates
			count = move(Rod_L, Rod_M, Rod_R, plates, index - 1, count);

			// move interested plates
			plates[index].now = plates[index].target;
			RefreshAndPrint(Rod_L, Rod_M, Rod_R, plates);
			count++;

			// set upper plates position follow interested plates
			plates[index - 1].target = plates[index].now;

			// move upper plates position follow interested plates
			count = move(Rod_L, Rod_M, Rod_R, plates, index - 1, count);

		} else {
			// move interested plates
			plates[index].now = plates[index].target;
			RefreshAndPrint(Rod_L, Rod_M, Rod_R, plates);
			count++;
		}

		return count;
	}

	public static void RefreshAndPrint(Rod Rod_L, Rod Rod_M, Rod Rod_R, Plate[] plates) {

		Rod_L.SetPlateStore(plates);
		Rod_M.SetPlateStore(plates);
		Rod_R.SetPlateStore(plates);

		// print Image
		for (int j = 0; j < plates.length + 2; j++) {
			if (j == plates.length + 1) {
				for (int i = 0; i < Rod_L.image[plates.length].length * 3; i++) {
					System.out.print('*');
				}
			} else {
				for (int r = 0; r < 3; r++) {
					switch (r) {
						case 0:
							for (int i = 0; i < Rod_L.image[j].length; i++) {
								System.out.print(Rod_L.image[j][i]);
							}
							break;
						case 1:
							for (int i = 0; i < Rod_M.image[j].length; i++) {
								System.out.print(Rod_M.image[j][i]);
							}
							break;
						case 2:
							for (int i = 0; i < Rod_R.image[j].length; i++) {
								System.out.print(Rod_R.image[j][i]);
							}
							break;
					}

				}
			}

			System.out.println();
		}
		System.out.println();
	}
}
