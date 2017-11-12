package dsim.model;

import dsim.dictionary.Patch;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Dag on 03.10.2017.
 */
public class PatchesTest {
    @Test
    public void testPhysicalIndex() {
        Patches patches = new Patches(7,5, true, true);

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 7; col++) {
                Patch patchByPhysicalIndex = patches.getPatchByPhysicalIndex(col, row);
                System.out.print(String.format("%2d/%2d ", patchByPhysicalIndex.getX(), patchByPhysicalIndex.getY()));
            }
            System.out.println();
        }

        assertThat(patches.getPatchByPhysicalIndex(0, 0).getX(), is(-3));
        assertThat(patches.getPatchByPhysicalIndex(0, 0).getY(), is(2));
        assertThat(patches.getPatchByPhysicalIndex(1, 0).getX(), is(-2));
        assertThat(patches.getPatchByPhysicalIndex(1, 0).getY(), is(2));
        assertThat(patches.getPatchByPhysicalIndex(1, 1).getX(), is(-2));
        assertThat(patches.getPatchByPhysicalIndex(1, 1).getY(), is(1));
        assertThat(patches.getPatchByPhysicalIndex(2, 1).getX(), is(-1));
        assertThat(patches.getPatchByPhysicalIndex(3, 2).getY(), is(0));
        assertThat(patches.getPatchByPhysicalIndex(4, 4).getY(), is(-2));
        assertThat(patches.getPatchByPhysicalIndex(6, 4).getX(), is(3));
        assertThat(patches.getPatchByPhysicalIndex(6, 4).getY(), is(-2));
    }

    @Test
    public void testJumpShortDistancde() {
        Patches patches = new Patches(10, 20, true, true);

        Position from = new Position(0, 0);
        Position to = patches.jump(from, 0, 0);
        assertThat(to, is(from));

        from = new Position(0, 0);
        to = patches.jump(from, 3, 0);
        assertThat(to, is(new Position(0, 3)));

        from = new Position(0, 0);
        to = patches.jump(from, 1, Math.toRadians(45));
        assertThat(to, is(new Position(0.70710, 0.70710)));

        from = new Position(0, 0);
        to = patches.jump(from, 3, Math.toRadians(90));
        assertThat(to, is(new Position(3, 0)));

        from = new Position(0, 0);
        to = patches.jump(from, 1, Math.toRadians(135));
        assertThat(to, is(new Position(0.70710, -0.70710)));

        from = new Position(0, 0);
        to = patches.jump(from, 3, Math.toRadians(180));
        assertThat(to, is(new Position(0, -3)));

        from = new Position(0, 0);
        to = patches.jump(from, 1, Math.toRadians(225));
        assertThat(to, is(new Position(-0.70710, -0.70710)));


        from = new Position(0, 0);
        to = patches.jump(from, 3, Math.toRadians(270));
        assertThat(to, is(new Position(-3, 0)));

        from = new Position(0, 0);
        to = patches.jump(from, 1, Math.toRadians(315));
        assertThat(to, is(new Position(-0.70710, 0.70710)));

        from = new Position(1, 0);
        to = patches.jump(from, 1, Math.toRadians(315));
        assertThat(to, is(new Position(1 - 0.70710, 0.70710)));

        from = new Position(1, 1);
        to = patches.jump(from, 1, Math.toRadians(315));
        assertThat(to, is(new Position(1 - 0.70710, 1 + 0.70710)));

        from = new Position(0, -1);
        to = patches.jump(from, 1, Math.toRadians(315));
        assertThat(to, is(new Position(-0.70710, -1 + 0.70710)));

        from = new Position(1, -1);
        to = patches.jump(from, 1, Math.toRadians(315));
        assertThat(to, is(new Position(1 - 0.70710, -1 + 0.70710)));

        from = new Position(-1, 0);
        to = patches.jump(from, 1, Math.toRadians(315));
        assertThat(to, is(new Position(-1 - 0.70710, 0.70710)));

        from = new Position(0, 1);
        to = patches.jump(from, 1, Math.toRadians(315));
        assertThat(to, is(new Position(-0.70710, 1 + 0.70710)));
    }

    @Test
    public void testJumpWithWrapY() {
        Patches wrappedPatches = new Patches(11, 21, true, true);

        Position from, to;

        from = new Position(0, 0);
        to = wrappedPatches.jump(from, 15, Math.toRadians(180));
        assertThat(to, is(new Position(0, 6)));

        from = new Position(0, 0);
        to = wrappedPatches.jump(from, 11, 0);
        assertThat(to, is(new Position(0, -10)));

        from = new Position(0, 0);
        to = wrappedPatches.jump(from, 0.1, 0);
        assertThat(to, is(new Position(0, 0.1)));

        from = new Position(0, 0);
        to = wrappedPatches.jump(from, 10.5, 0);
        assertThat(to, is(new Position(0, 10.5)));

        from = new Position(0, 0);
        to = wrappedPatches.jump(from, 10.6, 0);
        assertThat(to, is(new Position(0, -10.4)));

        from = new Position(0, 0);
        to = wrappedPatches.jump(from, 0, 0);
        assertThat(to, is(new Position(0, 0)));

        from = new Position(0, 0);
        to = wrappedPatches.jump(from, 21 * 17 + 0.1, 0);
        assertThat(to, is(new Position(0, 0.1)));
    }

    @Test
    public void testJumpWithWrapX() {
        Patches wrappedPatches = new Patches(21, 11, true, true);
        Position from, to;

        from = new Position(0, 0);
        to = wrappedPatches.jump(from, 10, Math.toRadians(90));
        assertThat(to, is(new Position(10, 0)));

        from = new Position(0, 0);
        to = wrappedPatches.jump(from, 0.1, Math.toRadians(90));
        assertThat(to, is(new Position(0.1, 0)));

        from = new Position(0, 0);
        to = wrappedPatches.jump(from, 10.5, Math.toRadians(90));
        assertThat(to, is(new Position(10.5, 0)));

        from = new Position(0, 0);
        to = wrappedPatches.jump(from, 10.6, Math.toRadians(90));
        assertThat(to, is(new Position(-10.4, 0)));

        from = new Position(0, 0);
        to = wrappedPatches.jump(from, -10.6, Math.toRadians(90));
        assertThat(to, is(new Position(10.4, 0)));

        from = new Position(0, 0);
        to = wrappedPatches.jump(from, 21*345, Math.toRadians(90));
        assertThat(to, is(new Position(0, 0)));

        from = new Position(0, 0);
        to = wrappedPatches.jump(from, 21 * 17 + 0.1, Math.toRadians(90));
        assertThat(to, is(new Position(0.1, 0)));
    }

    @Test
    public void  testGetPatchAt() {
        Patches patches = new Patches(11, 21, true, true);
        Patch patch;


        for (int row = 0; row < 21; row++) {
            for (int col = 0; col < 11; col++) {
                Patch patchByPhysicalIndex = patches.getPatchByPhysicalIndex(col, row);
                System.out.print(String.format("%2d/%2d|%2d/%2d ", col, row, patchByPhysicalIndex.getX(), patchByPhysicalIndex.getY()));
            }
            System.out.println();
        }
        patch = patches.getPatchAt(new Position(0, 0));
        assertThat(patch, is( patches.getPatchByPhysicalIndex(5, 10)));

        patch = patches.getPatchAt(new Position(-1, 1));
        assertThat(patch, is(patches.getPatchByPhysicalIndex(4,9)));

        patch = patches.getPatchAt(new Position(-5, 10));
        assertThat(patch, is(patches.getPatchByPhysicalIndex(0,0)));
    }
}