require 'rspec'

$:.unshift File.expand_path("../../lib")
require 'warehouse'
require 'order'

describe Order do
  let(:talisker) { "Talisker".freeze }

  let(:warehouse) do
    w = Warehouse.new
    w.add(talisker, 50)
    w
  end

  it "is not filled by default" do
    Order.new(talisker, 50).should_not be_filled
  end

  it "is filled if enough in warehouse" do
    order = Order.new(talisker, 50)
    order.fill(warehouse)
    order.should be_filled
    warehouse.inventory(talisker).should == 0
  end

  it "does not remove if not enough" do
    order = Order.new(talisker, 51)
    order.fill(warehouse)
    order.should_not be_filled
    warehouse.inventory(talisker).should == 50
  end
end
