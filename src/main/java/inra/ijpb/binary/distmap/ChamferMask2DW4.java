/*-
 * #%L
 * Mathematical morphology library and plugins for ImageJ/Fiji.
 * %%
 * Copyright (C) 2014 - 2023 INRA.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */
/**
 * 
 */
package inra.ijpb.binary.distmap;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Implementation of 2D Chamfer mask that manages four types of offsets types.
 * Weights correspond to the four following vectors:
 * <ol>
 * <li> d(1,0) </li>
 * <li> d(1,1) </li>
 * <li> d(2,1) </li>
 * <li> d(3,1) </li>
 * </ol>
 * 
 * By default, computation is performed using integers.
 * 
 * @author dlegland
 */
public class ChamferMask2DW4 extends ChamferMask2D
{
	/** The weight for orthogonal neighbors.*/
	short a;
	/** The weight for diagonal neighbors.*/
	short b;
	/** The weight for chess-knight move neighbors.*/
	short c;
	/** The weight for the shift (3,1).*/
	short d;
	
	/**
	 * Creates a new ChamferWeights2DW3 object by specifying the weights
	 * associated to orthogonal and diagonal neighbors.
	 * 
	 * @param a
	 *            the weight associated to orthogonal neighbors
	 * @param b
	 *            the weight associated to diagonal neighbors
     * @param c
     *            the weight associated to chess-knight move neighbors
     * @param d
     *            the weight associated to (3,1) shift and its permutations
	 */
	public ChamferMask2DW4(int a, int b, int c, int d)
	{
		this.a = (short) a;
		this.b = (short) b;
		this.c = (short) c;
		this.d = (short) d;
	}

	/**
	 * Creates a new ChamferMask3D object by specifying the weights
	 * associated to orthogonal and diagonal neighbors.
	 * 
	 * @param weights the weights associated to the different types of offset
	 */	
	public ChamferMask2DW4(short[] weights)
	{
		if (weights.length != 4)
		{
			throw new RuntimeException("Number of weights must be 4, not " + weights.length);
		}
		this.a = weights[0];
		this.b = weights[1];
		this.c = weights[2];
		this.d = weights[3];
	}

	@Override
	public Collection<ShortOffset> getForwardOffsets()
	{
		// create array of forward shifts
		ArrayList<ShortOffset> offsets = new ArrayList<ShortOffset>(12);
	
		// offsets in the (y-3)-line
		offsets.add(new ShortOffset(-1, -3, d));
		offsets.add(new ShortOffset(+1, -3, d));

		// offsets in the (y-2)-line
		offsets.add(new ShortOffset(-1, -2, c));
		offsets.add(new ShortOffset(+1, -2, c));
		
		// offsets in the (y-1)-line
		offsets.add(new ShortOffset(-3, -1, d));
		offsets.add(new ShortOffset(-2, -1, c));
		offsets.add(new ShortOffset(-1, -1, b));
		offsets.add(new ShortOffset( 0, -1, a));
		offsets.add(new ShortOffset(+1, -1, b));
		offsets.add(new ShortOffset(+2, -1, c));
		offsets.add(new ShortOffset(+3, -1, d));
		
		// offsets in the current line
		offsets.add(new ShortOffset(-1,  0, a));
	
		return offsets;
	}

	@Override
	public Collection<ShortOffset> getBackwardOffsets()
	{
		// create array of backward shifts
		ArrayList<ShortOffset> offsets = new ArrayList<ShortOffset>(12);

		// offsets in the (y-3)-line
		offsets.add(new ShortOffset(-1, +3, d));
		offsets.add(new ShortOffset(+1, +3, d));
		
		// offsets in the (y+2)-line
		offsets.add(new ShortOffset(-1, +2, c));
		offsets.add(new ShortOffset(+1, +2, c));
		
		// offsets in the (y+1)-line
		offsets.add(new ShortOffset(-3, +1, d));
		offsets.add(new ShortOffset(-2, +1, c));
		offsets.add(new ShortOffset(-1, +1, b));
		offsets.add(new ShortOffset( 0, +1, a));
		offsets.add(new ShortOffset(+1, +1, b));
		offsets.add(new ShortOffset(+2, +1, c));
		offsets.add(new ShortOffset(+3, +1, d));
		
		// offsets in the current line
		offsets.add(new ShortOffset(+1,  0, a));
		

		return offsets;
	}

	@Override
	public short getShortNormalizationWeight()
	{
		return a;
	}
}
