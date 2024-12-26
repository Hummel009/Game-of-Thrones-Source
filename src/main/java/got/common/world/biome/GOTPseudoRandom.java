package got.common.world.biome;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GOTPseudoRandom {
	private final int[] array;
	private int counter;

	public static final GOTPseudoRandom RANDOM_6 = new GOTPseudoRandom(6);
	public static final GOTPseudoRandom RANDOM_16 = new GOTPseudoRandom(16);
	public static final GOTPseudoRandom RANDOM_60 = new GOTPseudoRandom(60);
	public static final GOTPseudoRandom RANDOM_128 = new GOTPseudoRandom(128);

	private GOTPseudoRandom(int length) {
		List<Integer> list = new ArrayList<>(length);

		for (int i = 0; i < length; i++) {
			list.add(i);
		}

		Collections.shuffle(list);

		//noinspection StreamToLoop
		array = list.stream().mapToInt(Integer::intValue).toArray();
	}

	public int nextInt() {
		counter++;
		if (counter == array.length) {
			counter = 0;
		}
		return array[counter];
	}
}