/**
 * 
 */
package inra.ijpb.binary.distmap;

import static org.junit.Assert.*;

import org.junit.Test;

import ij.process.ByteProcessor;
import ij.process.ImageProcessor;

/**
 * @author dlegland
 *
 */
public class ChamferDistanceTransform2DShortTest
{
	/**
	 * Test method for {@link inra.ijpb.binary.distmap.ChamferDistanceTransform2DShort#distanceMap(ij.process.ImageProcessor)}.
	 */
	@Test
	public final void testDistanceMap_FromBorders_ChessBoard()
	{
		ByteProcessor image = new ByteProcessor(12, 10);
		image.setBackgroundValue(0);
		image.setValue(0);
		image.fill();
		for (int y = 2; y < 8; y++)
		{
			for (int x = 2; x < 10; x++)
			{
				image.set(x, y, 255);
			}
		}

		ChamferMask2D weights = ChamferMask2D.CHESSBOARD;
		DistanceTransform algo = new ChamferDistanceTransform2DShort(weights, true);
		ImageProcessor result = algo.distanceMap(image);

		assertNotNull(result);
		assertEquals(image.getWidth(), result.getWidth());
		assertEquals(image.getHeight(), result.getHeight());
		assertEquals(3, result.get(4, 4));
	}

	@Test
	public final void testDistanceMap_UntilCorners_CityBlock()
	{
		ByteProcessor image = new ByteProcessor(7, 7);
		image.setValue(255);
		image.fill();
		image.set(4, 4, 0);
		
		ChamferMask2D weights = ChamferMask2D.CITY_BLOCK;
		DistanceTransform algo = new ChamferDistanceTransform2DShort(weights, false);
		ImageProcessor result = algo.distanceMap(image);
		
		assertNotNull(result);
		assertEquals(image.getWidth(), result.getWidth());
		assertEquals(image.getHeight(), result.getHeight());
		assertEquals(8, result.get(0, 0));
		assertEquals(6, result.get(6, 0));
		assertEquals(6, result.get(0, 6));
		assertEquals(4, result.get(6, 6));
	}

	@Test
	public final void testDistanceMap_UntilCorners_Chessboard() 
	{
		ByteProcessor image = new ByteProcessor(7, 7);
		image.setValue(255);
		image.fill();
		image.set(4, 4, 0);
		
		ChamferMask2D weights = ChamferMask2D.CHESSBOARD;
		DistanceTransform algo = new ChamferDistanceTransform2DShort(weights, false);
		ImageProcessor result = algo.distanceMap(image);
		
		assertNotNull(result);
		assertEquals(image.getWidth(), result.getWidth());
		assertEquals(image.getHeight(), result.getHeight());
		assertEquals(4, result.get(0, 0));
		assertEquals(4, result.get(6, 0));
		assertEquals(4, result.get(0, 6));
		assertEquals(2, result.get(6, 6));
	}
	
	@Test
	public final void testDistanceMap_UntilCorners_Weights23() 
	{
		ByteProcessor image = new ByteProcessor(7, 7);
		image.setValue(255);
		image.fill();
		image.set(4, 4, 0);
		
		
		ChamferMask2D weights = new ChamferMask2DW2(2, 3);
		DistanceTransform algo = new ChamferDistanceTransform2DShort(weights, false);
		ImageProcessor result = algo.distanceMap(image);
		
		assertNotNull(result);
		assertEquals(image.getWidth(), result.getWidth());
		assertEquals(image.getHeight(), result.getHeight());
		assertEquals(12, result.get(0, 0));
		assertEquals(10, result.get(6, 0));
		assertEquals(10, result.get(0, 6));
		assertEquals(6, result.get(6, 6));
	}
	
	@Test
	public final void testDistanceMap_UntilCorners_Borgefors34()
	{
		ByteProcessor image = new ByteProcessor(7, 7);
		image.setValue(255);
		image.fill();
		image.set(4, 4, 0);
		
		
		ChamferMask2D weights = ChamferMask2D.BORGEFORS;
		DistanceTransform algo = new ChamferDistanceTransform2DShort(weights, false);
		ImageProcessor result = algo.distanceMap(image);
		
		assertNotNull(result);
		assertEquals(image.getWidth(), result.getWidth());
		assertEquals(image.getHeight(), result.getHeight());
		assertEquals(16, result.get(0, 0));
		assertEquals(14, result.get(6, 0));
		assertEquals(14, result.get(0, 6));
		assertEquals(8, result.get(6, 6));
	}

	@Test
	public final void testDistanceMap_UntilCorners_ChessKnight() 
	{
		ByteProcessor image = new ByteProcessor(7, 7);
		image.setValue(255);
		image.fill();
		image.set(4, 4, 0);

		ChamferMask2D weights = ChamferMask2D.CHESSKNIGHT;
		DistanceTransform algo = new ChamferDistanceTransform2DShort(weights, false);
		ImageProcessor result = algo.distanceMap(image);

		assertNotNull(result);
		assertEquals(image.getWidth(), result.getWidth());
		assertEquals(image.getHeight(), result.getHeight());
		assertEquals(10, result.get(4, 6), .01);
		assertEquals(14, result.get(6, 6), .01);
		assertEquals(28, result.get(0, 0), .01);
		assertEquals(22, result.get(6, 0), .01);
		assertEquals(22, result.get(0, 6), .01);
	}
}
