<template>
    <div>
        <h1>{{user.memberName}} ==> {{user.phoneNumber}}</h1>
    </div>
</template>

<script>
export default {
  name: 'DetailUser',
  data () {
    return {
      user: {}
    }
  },
  created () {
    var ageGroup = this.$route.params.ageGroup;

    this.$http.get(`/api/member/select/${ageGroup}`).then(resp => {
      this.user = resp.data.data;
      console.log("DATA :: ", this.user);
    })
  }
}
</script>

<style scoped>
</style>
