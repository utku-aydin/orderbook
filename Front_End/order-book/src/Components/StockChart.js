import React from "react";
import { render } from "react-dom";
import axios from "axios";
import "zingchart/es6";
import ZingChart from "zingchart-react";
// EXPLICITLY IMPORT MODULE from node_modules
import "zingchart/modules-es6/zingchart-maps.min.js";
import "zingchart/modules-es6/zingchart-maps-usa.min.js";

const SERVICE_URL = process.env.REACT_APP_SERVICE_URL;

class StockChart extends React.Component {
  constructor(props) {
    super(props);
    let { trades } = this.props;
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
    this.state.config.series.values = this.props.trades;
    this.setState(this.state);
    fetch(SERVICE_URL + `/count/${this.props.graphData.count}`)
      .then((data) => data.json())
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

    fetch(SERVICE_URL + `/count/${this.props.graphData.count}`)
      .then((data) => data.json())
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

    var valueArr2 = Array.from(this.props.trades);

    return (
      <div>
        <ZingChart data={this.state.config} />
      </div>
    );
  }
}

export default StockChart;
