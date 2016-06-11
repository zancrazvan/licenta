package com.binpacking.chromosome;

import java.util.ArrayList;
import java.util.List;

import com.binpacking.bin.Bin;
import com.binpacking.element.Element;
import com.binpacking.util.Randomizer;

public class ChromosomeMutator {

	public void mutate(Chromosome chromosome, int probability, int mutionSize) {

		ChromosomeDAO chromosomeDAO = new ChromosomeDAO();

		if (Randomizer.generate(0, 100) < probability) {

			int i = 0;
			List<Integer> selected = new ArrayList<>();

			while (i < mutionSize) {

				int position = Randomizer.generate(0, chromosome.getBins().size() - 1);

				if (!selected.contains(position)) {

					selected.add(position);
					i++;
				}

			}

			List<Element> elements = new ArrayList<>();
			List<Bin> toRemove = new ArrayList<>();

			// System.out.println("TO MUTATE: " + selected.size());
			for (Integer integer : selected) {
				toRemove.add(chromosome.getBins().get((int) integer));

			}
			for (Bin bin : toRemove) {

				elements.addAll(bin.getElements());
				bin.getElements().clear();
			}

			chromosomeDAO.addFreeItems(elements, chromosome);

		}

	}

}
