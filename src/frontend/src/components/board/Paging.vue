<template>
  <v-container class="mt-2">
    <v-item-group active-class="primary" mandatory>
      <v-btn text x-small v-on:click="goPage(1, myPaging.selected, myPaging.keyWord)"><v-icon>mdi-chevron-double-left</v-icon></v-btn>
      <v-btn class="ml-1" text x-small v-on:click="goPage(myPaging.prevPage, myPaging.selected, myPaging.keyWord)"><v-icon>mdi-chevron-left</v-icon></v-btn>
      <v-item
          v-for="index in myPaging.pageNumberList"
          v-bind:key="index"
          v-slot:default="{ active, toggle }"
      >
        <v-btn text
               x-small
              :input-value="active"
              @click="toggle"
              v-on:click="goPage(index, myPaging.selected, myPaging.keyWord)"
              class="ml-1 text-md-body-1"

        >
          {{ index }}
        </v-btn>
      </v-item>

      <v-btn class="ml-1" text x-small v-on:click="goPage(myPaging.nextPage, myPaging.selected, myPaging.keyWord)"><v-icon>mdi-chevron-right</v-icon></v-btn>
      <v-btn class="ml-1" text x-small v-on:click="goPage(myPaging.endPage, myPaging.selected, myPaging.keyWord)"><v-icon>mdi-chevron-double-right</v-icon></v-btn>

    </v-item-group>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      myPaging: {
        pageNumber: 1, //현재 페이지 번호
        pageSize: 10, //페이지 사이즈
        totalPages: 0, //전체 페이지 개수
        totalElements: 0, //전체 게시물 개수
        firstPage: 1, //첫 페이지
        prevPage: 0, //이전 페이지(이전 페이지 블록)
        nextPage: 0, //다음 페이지(다음 페이지 블록)
        endPage: 0, //끝 페이지
        pageNumberList: [1], //페이지 번호 리스트
        selected: '',
        keyWord: '',
        
      }
    }
  },
  created() {
    this.myPaging = this.$store.getters.getPaging

  },
  methods: {
    //이벤트 버스
    goPage(pageNo, selected, keyWord) {
      this.$EventBus.$emit('goPage', (pageNo - 1), selected, keyWord)
    },
  }
}
</script>

<style scoped>

</style>