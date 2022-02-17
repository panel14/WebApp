<template>
  <canvas @click="catchClick" ref="graphCanvas" width="400" height="400"></canvas>
</template>

<script>

export default {
  name: "Graph",
  mounted() {
    let vm = this
    vm.canvas = vm.$refs.graphCanvas
    this.canvasObj = vm.canvas
    this.canvasContext = vm.canvas.getContext('2d')
    this.canvasWidth = vm.canvas.width
    this.canvasHeight = vm.canvas.height
    this.printField(this.canvasContext, this.canvasWidth, this.canvasHeight)
    this.printGraph(this.canvasContext, this.canvasWidth, this.canvasHeight, this.radius)
    this.radius = null
  },
  data() {
    return {
      canvasObj: null,
      canvasWidth: null,
      canvasHeight: null,
      canvasContext: null,
      scaleText: 0.10,
      radius: 3
    }
  },
  methods: {
    printField(context, width, height){
      for (let x = 0.5; x < width; x+= 10){
        context.moveTo(x, 0);
        context.lineTo(x, height);
      }
      for (let y = 0.5; y < height; y+= 10){
        context.moveTo(0, y);
        context.lineTo(width, y);
      }
      context.strokeStyle = "#404040";
      context.stroke();

      context.fillStyle = "#0366d6";
      context.font = "bold 12px sans-serif";
      context.textBaseline = "middle";
      context.textAlign = "center";

      context.beginPath();
      context.moveTo(width / 2, height);                  //y-coordLine
      context.lineTo(width / 2, height * 0.85)
      this.getNickV(context, width / 2, height * 0.85);
      context.fillText("-3", width / 2 + 15, height * 0.85);

      context.lineTo(width / 2, height * 0.675);
      this.getNickV(context, width / 2, height * 0.675);
      context.fillText("-3/2", width / 2 + 20, height * 0.675);

      context.lineTo(width / 2, height * 0.325);
      this.getNickV(context, width / 2, height * 0.325);
      context.fillText("3/2", width / 2 + 20, height * 0.325);

      context.lineTo(width / 2, height * 0.15);
      this.getNickV(context, width / 2, height * 0.15);
      context.fillText("3", width / 2 + 15, height * 0.15);

      context.lineTo(width / 2, height * 0.125);
      context.moveTo(width / 2, height * 0.075);
      context.lineTo(width / 2, 0);

      context.moveTo(width / 2 - 5, 5);                //y-arrow
      context.lineTo(width / 2, 0);
      context.lineTo(width / 2 + 5, 5);

      context.moveTo(0, height / 2);                   //x-coordLine
      context.lineTo(width * 0.15, height / 2);
      this.getNickG(context, width * 0.15, height / 2);
      context.fillText("-3", width * 0.15 , height / 2 - 15);

      context.lineTo(width * 0.325, height / 2);
      this.getNickG(context, width * 0.325, height / 2);
      context.fillText("-3/2", width * 0.325 , height / 2 - 15);

      context.lineTo(width * 0.675, height / 2);
      this.getNickG(context, width * 0.675, height / 2);
      context.fillText("3/2", width * 0.675 , height / 2 - 15);

      context.lineTo(width * 0.85, height / 2);
      this.getNickG(context, width * 0.85, height / 2);
      context.fillText("3", width * 0.85 , height / 2 - 15);

      context.lineTo(width * 0.875, height / 2);
      context.moveTo(width * 0.925, height / 2);
      context.lineTo(width, height / 2);

      context.moveTo(width - 5, height / 2 - 5);       //x-arrow
      context.lineTo(width, height / 2);
      context.lineTo(width -5, height / 2 + 5);

      context.strokeStyle = "#0366d6";
      context.stroke();

      context.fillText("x", width * (1 - this.scaleText) , height / 2);
      context.fillText("y", width / 2, height * this.scaleText);
      context.fillText("(0 ; 0)", width / 2 - 20, height / 2 + 15);
    },

    getNickV(context, width, height){
      context.moveTo(width - 5, height);
      context.lineTo(width + 5, height);
      context.moveTo(width, height)
    },

    getNickG(context, width, height){
      context.moveTo(width, height - 5);
      context.lineTo(width, height + 5);
      context.moveTo(width, height);
    },

    changeGraph(radius){

      this.radius = radius

      let rad = parseInt(radius)

      if (rad <= 0){
        this.$emit('invalidRadius', 'Значение R меньше либо равно 0')
        return
      }
      this.canvasContext.clearRect(0, 0, this.canvasWidth, this.canvasHeight)
      this.printField(this.canvasContext, this.canvasWidth, this.canvasHeight)
      this.printGraph(this.canvasContext, this.canvasWidth, this.canvasHeight, radius)
      this.radius = radius
    },

    printGraph(context, width, height, radius){
      let rad = parseInt(radius)

/*      const scaleRad = 1 + (3 - radius) * 0.1
      const scaleR = 0.15 //!* scaleRad * (1 + (3 - radius) * 0.25)
      const scaleR2 = 0.325 //!* scaleRad

      context.moveTo(width / 2, height * (1 - scaleR));
      context.lineTo( width * (1 - scaleR2),height / 2);
      context.arc(width / 2, height / 2, height * (0.5 - s  caleR2), 0 ,
          Math.PI * 3 / 2, true)
      context.moveTo(width / 2, height * scaleR2)
      context.lineTo(width * scaleR , height * scaleR2)
      context.lineTo(width * scaleR, height / 2)*/
      context.moveTo(width / 2, height * (0.5 + 0.35 * rad / 3));
      context.lineTo( width * (0.5 + 0.175 * rad / 3),height / 2);
      context.arc(width / 2, height / 2, height * 0.175 * rad / 3, 0 ,
          Math.PI * 3 / 2, true)
      context.moveTo(width / 2, height * (0.325 + 0.175 * (3 - rad) / 3))
      context.lineTo(width * (0.15 + 0.325 * (3 - rad) / 3) , height * (0.325 + 0.175 * (3 - rad) / 3))
      context.lineTo(width * (0.15 + 0.325 * (3 - rad) / 3), height / 2)

      context.strokeStyle = "#0366d6"
      context.stroke()
    },

    catchClick(e){
      if (this.radius === null){
        this.$emit('invalidRadius', 'Значение R не установлено')
        return
      }
      let rad = parseInt(this.radius)
      if (rad <= 0){
        return
      }

      let x = e.pageX - this.canvasObj.offsetLeft
      let y = e.pageY - this.canvasObj.offsetTop

      let point = this.convertSend(x, y, rad)
      point.x = point.x.toFixed(2)
      point.y = point.y.toFixed(2)

      this.sendData(point)
    },

    convertSend(x, y, r){
      x -= this.canvasWidth / 2
      x = (x * r) / ( this.canvasWidth * 0.35 * r/3)

      y = -y + this.canvasHeight / 2
      y = (y * r) / ( this.canvasHeight * 0.35 * r/3)
      return {
        x: x,
        y: y,
        r: r
      }
    },

    convertPrint(coords){
      coords[0] = (coords[0] * this.canvasWidth * 0.35 * coords[2] / 3) / coords[2]
      coords[0] += this.canvasWidth / 2

      coords[1] = (coords[1] * this.canvasHeight * 0.35 * coords[2] / 3) / coords[2]
      coords[1] = - coords[1] + this.canvasHeight / 2

      return [coords[0], coords[1]]
    },

    printPoint(coords, result){
      let point = this.convertPrint(coords)
      this.canvasContext.beginPath()
      if (result)
        this.canvasContext.fillStyle = '#04a242'
      else
        this.canvasContext.fillStyle = '#ff5555'
      this.canvasContext.arc(point[0], point[1], 3, Math.PI * 2, 0, false)
      this.canvasContext.fill()
    },

    async sendData(point){
      const data = (await this.$api.auth.sendPoint(point)).data
      let coords = [data.point.x, data.point.y, data.point.r]
      this.printPoint(coords, data.point.result)
      this.$emit('transPoint', data)
    }
  }
}
</script>

<style scoped>

</style>