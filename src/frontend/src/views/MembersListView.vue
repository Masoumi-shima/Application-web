<template>
  <table class="table-style">
    <thead class="table-head">
    <tr>
      <th>Prénom</th>
      <th>Nom</th>
      <th>Date de naissance</th>
      <th>Adresse courriel</th>
      <th>Genre</th>
      <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <tr v-for="member in members" :key="member.permitNumber">
      <td>{{member.firstName}}</td>
      <td>{{member.lastName}}</td>
      <td>{{member.birthDate}}</td>
      <td>{{member.email}}</td>
      <td>{{member.gender}}</td>
      <td>
        <router-link :to="'/membres/' + member.permitNumber">Voir </router-link>
        <router-link :to="'/membres/modifier/' + member.permitNumber">Modifier</router-link>
      </td>
    </tr>
    </tbody>
  </table>
  <MemberDetails v-if="this.$route.params.id != null" />
</template>

<script>
import MemberService from '../services/MemberService'
import MemberDetails from '@/components/MemberDetails'

export default {
  name: 'MembersList',
  data() {
    return {
      members: []
    }
  },
  components: {
    MemberDetails
  },
  methods: {
    async getMembers() {
      await MemberService.getMembers()
          .then((response) => {
            this.members = response.data
          })
          .catch(e => {
            alert(e)
          })
    }
  },
  created() {
    this.getMembers()
  }
}
</script>

<style scoped>
table {
  border-collapse: collapse;
  min-width: 200px;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
  float: left;
  margin-top: 50px;
  margin-left: 2px;
  padding: 10px;
  border: 1px solid  rgb(35, 36, 37);
}

.table-head tr {
  background-color: black;
  color: aliceblue ;
  text-align: left;
}

.table-style th,
.table-style td {
  padding: 12px 15px;
}

.table-style tbody tr {
  border-bottom: 1px solid aliceblue;
}

.table-style tbody tr:nth-of-type(even) {
  background-color: #242424;
}

.table-style tbody tr:last-of-type {
  border-bottom: 2px solid #050505;
}

.table-style tbody tr.active-row {
  font-weight: bold;
  color: #36373d;
}
</style>