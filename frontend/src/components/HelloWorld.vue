<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <h2>Vue JS Test</h2>
    <h3 v-for="index in 5" :key="index">
      <router-link :to="{ name: 'DetailUser', params: { ageGroup: index * 10}}"> 
        {{index * 10}} 대
      </router-link>
    </h3>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      members: []
    }
  },
  created () {
    this.$http.get('/api/member/select').then(resp => {
      this.members = resp.data.data;
      console.log("DATA :: ", this.members);
    })
  }
}
</script>

<style scoped>
h1,
h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
