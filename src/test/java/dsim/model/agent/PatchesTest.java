package dsim.model.agent;

import dsim.model.Position;
import dsim.util.Util;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Dag on 03.10.2017.
 */
public class PatchesTest {
    private Patches wrappedPatches = new Patches(10, 20, true, true);

    @Test
    public void testPhysicalIndex() {
        Patches patches = new Patches(5, 7, true, true);

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 7; col++) {
                Patch patchByPhysicalIndex = patches.getPatchByPhysicalIndex(row, col);
                System.out.print(String.format("%2d/%2d ", patchByPhysicalIndex.getX(), patchByPhysicalIndex.getY()));
            }
            System.out.println();
        }

        assertThat(patches.getPatchByPhysicalIndex(0, 0).getX(), is(-3));
        assertThat(patches.getPatchByPhysicalIndex(0, 0).getY(), is(2));
        assertThat(patches.getPatchByPhysicalIndex(1, 0).getX(), is(-3));
        assertThat(patches.getPatchByPhysicalIndex(1, 0).getY(), is(1));
        assertThat(patches.getPatchByPhysicalIndex(1, 1).getX(), is(-2));
        assertThat(patches.getPatchByPhysicalIndex(1, 1).getY(), is(1));
        assertThat(patches.getPatchByPhysicalIndex(2, 1).getX(), is(-2));
        assertThat(patches.getPatchByPhysicalIndex(3, 2).getY(), is(-1));
        assertThat(patches.getPatchByPhysicalIndex(4, 4).getY(), is(-2));
        assertThat(patches.getPatchByPhysicalIndex(4, 6).getX(), is(3));
        assertThat(patches.getPatchByPhysicalIndex(4, 6).getY(), is(-2));
    }

    @Test
    public void testJumpShortDistancde() {
        Position from = new Position(0, 0);
        Position to = wrappedPatches.jump(from, 0, 0);
        assertThat(to, is(from));

        from = new Position(0, 0);
        to = wrappedPatches.jump(from, 3, 0);
        assertThat(to, is(new Position(0, 3)));

        from = new Position(0, 0);
        to = wrappedPatches.jump(from, 1, Util.toRadians(45));
        assertThat(to, is(new Position(0.70710, 0.70710)));

        from = new Position(0, 0);
        to = wrappedPatches.jump(from, 3, Util.toRadians(90));
        assertThat(to, is(new Position(3, 0)));

        from = new Position(0, 0);
        to = wrappedPatches.jump(from, 1, Util.toRadians(135));
        assertThat(to, is(new Position(0.70710, -0.70710)));

        from = new Position(0, 0);
        to = wrappedPatches.jump(from, 3, Util.toRadians(180));
        assertThat(to, is(new Position(0, -3)));

        from = new Position(0, 0);
        to = wrappedPatches.jump(from, 1, Util.toRadians(225));
        assertThat(to, is(new Position(-0.70710, -0.70710)));


        from = new Position(0, 0);
        to = wrappedPatches.jump(from, 3, Util.toRadians(270));
        assertThat(to, is(new Position(-3, 0)));

        from = new Position(0, 0);
        to = wrappedPatches.jump(from, 1, Util.toRadians(315));
        assertThat(to, is(new Position(-0.70710, 0.70710)));

        from = new Position(1, 0);
        to = wrappedPatches.jump(from, 1, Util.toRadians(315));
        assertThat(to, is(new Position(1-0.70710, 0.70710)));

        from = new Position(1, 1);
        to = wrappedPatches.jump(from, 1, Util.toRadians(315));
        assertThat(to, is(new Position(1-0.70710, 1+0.70710)));

        from = new Position(0, -1);
        to = wrappedPatches.jump(from, 1, Util.toRadians(315));
        assertThat(to, is(new Position(-0.70710, -1+0.70710)));

        from = new Position(1, -1);
        to = wrappedPatches.jump(from, 1, Util.toRadians(315));
        assertThat(to, is(new Position(1-0.70710, -1+0.70710)));

        from = new Position(-1, 0);
        to = wrappedPatches.jump(from, 1, Util.toRadians(315));
        assertThat(to, is(new Position(-1-0.70710, 0.70710)));

        from = new Position(0, 1);
        to = wrappedPatches.jump(from, 1, Util.toRadians(315));
        assertThat(to, is(new Position(-0.70710, 1+0.70710)));
    }

    @Test
    public void testJumpWithWrap() {
        Position from = new Position(0, 0);
        Position to = wrappedPatches.jump(from, 10, 0);
        assertThat(to, is(new Position(0,-1)));

         from = new Position(0, 0);
         to = wrappedPatches.jump(from, 10 * 17, 0);
        assertThat(to, is(new Position(0,-1)));

    }

}