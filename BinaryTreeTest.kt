import org.junit.Assert
import org.junit.Test

class BinaryTreeTest {

    @Test
    fun add() {
        val tree = BinaryTree<Int>()
        tree.add(10)
        tree.add(5)
        tree.add(7)
        tree.add(10)
        Assert.assertEquals(3, tree.size)
        Assert.assertTrue(tree.contains(5))
        tree.add(3)
        tree.add(1)
        tree.add(3)
        tree.add(4)
        Assert.assertEquals(6, tree.size)
        Assert.assertFalse(tree.contains(8))
        tree.add(8)
        tree.add(15)
        tree.add(15)
        tree.add(20)
        Assert.assertEquals(9, tree.size)
        Assert.assertTrue(tree.contains(8))
        Assert.assertTrue(tree.checkInvariant())
        Assert.assertEquals(1, tree.first())
        Assert.assertEquals(20, tree.last())
    }

    @Test
    fun remove()  {
        val tree1 = BinaryTree<Int>()
        tree1.add(3)
        tree1.add(8)
        tree1.add(99)
        tree1.add(5)
        tree1.add(6)
        tree1.add(1)
        tree1.add(2)
        tree1.add(18)
        tree1.add(20)

        tree1.remove(8)
        Assert.assertEquals(1, tree1.first())
        Assert.assertEquals(8, tree1.size)
        Assert.assertFalse(tree1.contains(8))
        Assert.assertTrue(tree1.checkInvariant())

        tree1.remove(18)
        Assert.assertEquals(1, tree1.first())
        Assert.assertEquals(7, tree1.size)
        Assert.assertFalse(tree1.contains(18))
        Assert.assertTrue(tree1.checkInvariant())

        tree1.remove(3)
        Assert.assertEquals(1, tree1.first())
        Assert.assertEquals(6, tree1.size)
        Assert.assertFalse(tree1.contains(3))
        Assert.assertTrue(tree1.checkInvariant())

        tree1.remove(1)
        Assert.assertEquals(2, tree1.first())
        Assert.assertEquals(5, tree1.size)
        Assert.assertFalse(tree1.contains(1))
        Assert.assertTrue(tree1.checkInvariant())

        tree1.remove(20)
        Assert.assertEquals(2, tree1.first())
        Assert.assertEquals(4, tree1.size)
        Assert.assertFalse(tree1.contains(20))
        Assert.assertTrue(tree1.checkInvariant())

        tree1.remove(5)
        Assert.assertEquals(2, tree1.first())
        Assert.assertEquals(3, tree1.size)
        Assert.assertFalse(tree1.contains(5))
        Assert.assertTrue(tree1.checkInvariant())

        tree1.remove(2)
        Assert.assertEquals(6, tree1.first())
        Assert.assertEquals(2, tree1.size)
        Assert.assertFalse(tree1.contains(2))
        Assert.assertTrue(tree1.checkInvariant())

        tree1.remove(99)
        Assert.assertEquals(6, tree1.first())
        Assert.assertEquals(1, tree1.size)
        Assert.assertFalse(tree1.contains(99))
        Assert.assertTrue(tree1.checkInvariant())

        tree1.remove(6)
        Assert.assertEquals(0, tree1.size)


        val tree = BinaryTree<Int>()
        tree.add(10)
        tree.add(5)
        tree.add(7)
        tree.add(3)
        tree.add(1)
        tree.add(4)
        tree.add(8)
        tree.add(15)
        tree.add(20)

        tree.remove(10)
        Assert.assertEquals(1, tree.first())
        Assert.assertEquals(8, tree.size)
        Assert.assertFalse(tree.contains(10))
        Assert.assertTrue(tree.checkInvariant())

        tree.remove(20)
        Assert.assertEquals(15, tree.last())
        Assert.assertEquals(7, tree.size)
        Assert.assertTrue(tree.checkInvariant())
        Assert.assertFalse(tree.contains(20))

        tree.remove(15)
        Assert.assertFalse(tree.contains(15))
        Assert.assertEquals(8, tree.last())
        Assert.assertEquals(6, tree.size)

        tree.add(6)
        tree.remove(5)
        Assert.assertEquals(6, tree.size)
        Assert.assertEquals(1, tree.first())

        tree.remove(3)
        Assert.assertEquals(1, tree.first())
        Assert.assertEquals(8, tree.last())
        Assert.assertTrue(tree.checkInvariant())
        Assert.assertTrue(tree.contains(4))
        Assert.assertTrue(tree.contains(1))
        Assert.assertEquals(5, tree.size)

        tree.remove(4)
        Assert.assertEquals(1, tree.first())
        Assert.assertEquals(8, tree.last())
        Assert.assertFalse(tree.contains(4))

        tree.remove(7)
        Assert.assertFalse(tree.contains(7))
        Assert.assertEquals(3, tree.size)
        Assert.assertEquals(1, tree.first())
        Assert.assertEquals(8, tree.last())

        tree.remove(1)
        tree.remove(6)
        Assert.assertEquals(8, tree.first())
        Assert.assertEquals(8, tree.last())
        Assert.assertFalse(tree.contains(6))
        Assert.assertEquals(1, tree.size)

        tree.remove(8)
        Assert.assertEquals(0, tree.size)

    }
}