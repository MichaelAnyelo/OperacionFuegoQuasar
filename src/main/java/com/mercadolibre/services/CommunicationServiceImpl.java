package com.mercadolibre.services;

import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer.Optimum;

import java.util.List;

import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.stereotype.Service;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.mercadolibre.models.EnumSatellites;

@Service
public class CommunicationServiceImpl implements CommunicationService {

	@Override
	public double[] GetLocation(double[] distances) {

		double[][] positions = { { EnumSatellites.kenobi.getPositionx(), EnumSatellites.kenobi.getPositiony() },
				{ EnumSatellites.skywalker.getPositionx(), EnumSatellites.skywalker.getPositiony() },
				{ EnumSatellites.sato.getPositionx(), EnumSatellites.sato.getPositiony() } };

		NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(
				new TrilaterationFunction(positions, distances), new LevenbergMarquardtOptimizer());
		Optimum optimum = solver.solve();

		return optimum.getPoint().toArray();
	}

	@Override
	public String GetMessage(List<String[]> messages) {

		String[] aux = null;

		String msg = "";

		for (String[] m : messages) {

			if (aux == null) {
				aux = m;
			} else {
				for (int j = 0; j <= m.length - 1; j++) {

					if (!m[j].equals("")) {
						aux[j] = m[j];

					}
				}
			}

		}
		for (int i = 0; i < aux.length; i++) {
			msg += aux[i] + " ";
		}

		return msg;
	}

}
