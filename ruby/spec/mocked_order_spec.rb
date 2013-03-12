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
    warehouse.should_receive(:has_inventory?).with(talisker, 50).and_return(true)
    warehouse.should_receive(:remove).with(talisker, 50)
    order.fill(warehouse)
    order.should be_filled
  end

  it "does not remove if not enough" do
    order = Order.new(talisker, 51)
    warehouse.should_receive(:has_inventory?).with(talisker, 51).and_return(false)
    order.fill(warehouse)
    order.should_not be_filled
  end
end

