import React from "react";
import { render } from "react-dom";
import axios from "axios";
import "zingchart/es6";
import ZingChart from "zingchart-react";
// EXPLICITLY IMPORT MODULE from node_modules
import "zingchart/modules-es6/zingchart-maps.min.js";
import "zingchart/modules-es6/zingchart-maps-usa.min.js";

class StockChart extends React.Component {
  constructor(props) {
    super(props);
    // var valueArr = Array.from(this.props.trades);
    // for (var i = 0; i < valueArr.length; i++)
    //   valueArr[i] = parseInt(valueArr[i], 10);
    // console.log("Value Arr in constructor:");
    // console.log(valueArr);
    this.state = {
      orders: [],
      config: {
        type: "line"
      },
      series: [{
          values: [this.props.trades]
        }]
    };
  }
  render() {
    //var valueArr = [1, 2, 3, 4, 5];
    console.log("Rending Graph:");
    console.log(this.props.trades);
    //console.log(valueArr);
    console.log(this.state.config);
    var valueArr2 = Array.from(this.props.trades);
    console.log("Value Arr in constructor:");
    console.log(valueArr2);
    this.state.series.values = valueArr2;
    console.log("Series Arr in component:");
    console.log(this.state.series.values);
    
    //this.setState({values : valueArr2});
    // this.chart.current.addplot({
    //   data: {
    //     values: valueArr2,
    //     text: "My new plot"
    //   }
    // });
    return (
      <div>
        <div>{JSON.stringify(this.state.series.values)}</div>
        <ZingChart ref={this.chart} data={this.state.config} series={this.state.series} />
      </div>
    );
  }
}

export default StockChart;
