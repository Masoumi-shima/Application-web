<template>
  <div class="details" v-if="currentMember">
    <h1>{{currentMember.firstName}} {{currentMember.lastName}}</h1>
    <p>Prénom : {{currentMember.firstName}}</p>
    <p>Nom : {{currentMember.lastName}}</p>
    <p>Date de naissance : {{currentMember.birthDate}}</p>
    <p>Adresse Courriel : {{currentMember.email}}</p>
    <p>Genre : {{currentMember.gender}}</p>
    <p>A réussi l'examen : {{currentMember.passedExam}}</p>
    <Button class="delete" title="Supprimer" @click="deleteMember(this.$route.params.id)"></Button>
  </div>
</template>

<script>
import MemberService from "@/services/MemberService";
import Button from "@/components/Button";

export default {
  name: 'MemberDetails',
  components: {Button},
  data() {
    return {
      currentMember: null
    }
  },
  methods: {
    async getMember(permitNumber) {
      await MemberService.getAMember(permitNumber)
          .then((response) => {
            this.currentMember = response.data
          })
          .catch(e => {
            console.log(e)
          })
    },
    async deleteMember(permitNumber) {
      await MemberService.deleteMember(permitNumber)
          .catch(e => {
            console.log(e)
          })
      this.$router.back()

    }
  },
  created() {
    this.getMember(this.$route.params.id)
  },
  beforeRouteUpdate (to, from, next) {
    this.getMember(to.params.id)
    next()
  }
}
</script>

<style>
.details {
  float: left;
  margin: 50px;
}

.delete {
  font-size: 17px;
  color: white;
  padding: 10px;
  background: darkred;
  border: 1px solid rgb(0, 0, 0);
  border-radius: 10px;
  margin: 10px;
  transition: all 0.5s;
  cursor: pointer;
}
</style>