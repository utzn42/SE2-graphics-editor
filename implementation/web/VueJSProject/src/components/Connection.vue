<template>
  <div class="connection">
    <h1>Project {{projectID}}</h1>
  </div>
</template>

<script>
  import {dataBus} from "../main";

  export default {
    name: "Connection",
    props: {
      baseUrl: String
    },
    data() {
      return {
        projectID: ""
      }
    },
    methods: {
      createCanvas: function() {
        this.$http.get(this.baseUrl + "/test")
          .then(response => {
            console.log("GET REQUEST TO URL=\"" + this.baseUrl + "/test\":");
            console.log(response);
            this.projectID = response.body.projectID;
            dataBus.$emit('response', response);
          })
      },
      postRequest: function(requestType, data) {
        console.log("POST REQUEST TO URL=\"" + this.baseUrl + "/" + requestType + "/" + this.projectID + "\":");
        console.log("SENDING DATA:");
        console.log(data);
        this.$http.post(this.baseUrl + "/" + requestType + "/" + this.projectID, data)
          .then(response => {
            console.log("DATA RECEIVED:");
            console.log(response);
            dataBus.$emit('response', response);
          })
      }
    },
    created: function () {
      dataBus.$on('create', () => this.createCanvas());
      dataBus.$on('editCanvas', (data) => this.postRequest('editCanvas', data));
      dataBus.$on('addLayer', (data) => this.postRequest('addLayer', data));
      dataBus.$on('editLayer', (data) => this.postRequest('editLayer', data));
      dataBus.$on('deleteLayer', (data) => this.postRequest('deleteLayer', data));
      dataBus.$on('addShape', (data) => this.postRequest('addShape', data));
      dataBus.$on('editShape', (data) => this.postRequest('editShape', data));
      dataBus.$on('transformShape', (data) => this.postRequest('transformShape', data));
      dataBus.$on('deleteShape', (data) => this.postRequest('deleteShape', data));
    }
  }
</script>

<style scoped>
  .connection {
    display: none;
  }
</style>
