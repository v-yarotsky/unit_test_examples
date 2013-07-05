require 'minitest/unit'
require 'minitest/pride'
require 'minitest/autorun'

$:.unshift File.expand_path("../../lib")
require 'warehouse'
require 'order'

class TestOrder < Minitest::Unit::TestCase
  TALISKER = "Talisker".freeze

  def setup
    @warehouse = Warehouse.new
    @warehouse.add(TALISKER, 50)
  end

  def test_order_not_filled_initially
    order = Order.new(TALISKER, 50)
    refute order.filled?
  end

  def test_order_is_filled_if_enough_in_warehouse
    order = Order.new(TALISKER, 50)
    order.fill(@warehouse)
    assert order.filled?, "Order must be filled"
    assert_equal 0, @warehouse.inventory(TALISKER)
  end

  def test_order_does_not_remove_if_not_enough
    order = Order.new(TALISKER, 51)
    order.fill(@warehouse)
    refute order.filled?, "Order must not be filled"
    assert_equal 50, @warehouse.inventory(TALISKER)
  end
end

