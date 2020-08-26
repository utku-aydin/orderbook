import React from "react";
import { render } from "react-dom";
import axios from "axios";
import "zingchart/es6";
import ZingChart from "zingchart-react";
// EXPLICITLY IMPORT MODULE from node_modules
import "zingchart/modules-es6/zingchart-maps.min.js";
import "zingchart/modules-es6/zingchart-maps-usa.min.js";

const SERVICE_URL = "http://localhost:8080/api";

class StockChart extends React.Component {
  constructor(props) {
    super(props);
    let { trades } = this.props;
    //let valueArr = this.state.trades;
    //var valueArr = [1, 2, 3, 4, 5];
    //for (var i = 0; i < valueArr.length; i++)
    //valueArr[i] = parseInt(this.props.trades[1]);
    //valueArr[3] = 10;
    //console.log("Value Arr in constructor:");
    //console.log(valueArr);
    //console.log("Prop Trades in constructor:");
    //console.log(this.props.trades);
    //console.log("Props in constructor:");
    //console.log(this.props);
    this.state = {
      orders: [],
      config: {
        type: "line",
        series: [
          {
            values: [],
          },
        ],
      },
      trades: [],
    };
  }
  componentWillReceiveProps(nextProps) {
    if (nextProps.trades !== this.props.trades) {
      this.setState({
        config: {
          type: "line",
          series: [
            {
              values: this.props.trades,
            },
          ],
        },
      });
    }
    this.setState({
      config: {
        type: "line",
        series: [
          {
            values: this.props.trades,
          },
        ],
      },
    });
    console.log("Component will recieve called with: ", this.props.trades);
    this.state.config.series.values = this.props.trades;
    this.setState(this.state);
    fetch(SERVICE_URL + "/interval/10/5")
      .then((data) => data.json())
      //.then((data) => console.log(data.json()))
      .then((data) => {
        this.setState({
          trades: data,
          config: {
            type: "line",
            series: [
              {
                values: this.state.trades.reverse(),
              },
            ],
          },
        });
      })
      .catch((error) => {
        console.log("error:", error);
      });
  }
  componentDidMount() {
    this.setState({
      config: {
        type: "line",
        series: [
          {
            values: this.props.trades,
          },
        ],
      },
    });

    fetch(
      SERVICE_URL +
        `/interval/${this.props.graphData.interval}/${this.props.graphData.count}`
    )
      .then((data) => data.json())
      //.then((data) => console.log(data.json()))
      .then((data) => {
        this.setState({
          trades: data,
          config: {
            type: "line",
            series: [
              {
                values: this.state.trades.reverse(),
              },
            ],
          },
        });
      })
      .catch((error) => {
        console.log("error:", error);
      });
  }
  render() {
    let { trades } = this.props;
    console.log("Rending Graph:");
    console.log(this.props.trades);
    //console.log(valueArr);
    console.log(this.state.trades);
    var valueArr2 = Array.from(this.props.trades);
    console.log("Value Arr2:");
    console.log(valueArr2);
    return (
      <div>
        <ZingChart data={this.state.config} />
      </div>
    );
  }
}

export default StockChart;
