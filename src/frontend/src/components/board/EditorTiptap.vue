<template>
  <v-container style="width: 60%" class="editor">
    <v-row>
      <editor-menu-bar :editor="editor" v-slot="{ commands, isActive }">
        <div >
          <v-btn text icon
                 :class="{ 'is-active': isActive.heading({ level: 1 }) }"
                 @click="commands.heading({level: 1})"
          >
            <b> H1 </b>
          </v-btn>
          <v-btn text icon
                 :class="{ 'is-active': isActive.heading({ level: 2 }) }"
                 @click="commands.heading({level: 2})"
          >
            <b> H2 </b>
          </v-btn>
          <v-btn text icon
                 :class="{ 'is-active': isActive.heading({ level: 3 }) }"
                 @click="commands.heading({level: 3})"
          >
            <b> H3 </b>
          </v-btn>
          <v-btn text icon
                 :class="{ 'is-active': isActive.bold() }"
                 @click="commands.bold"
          >
            <v-icon>mdi-format-bold</v-icon>
          </v-btn>

          <v-btn text icon
                 :class="{ 'is-active': isActive.underline() }"
                 @click="commands.underline"
          >
            <v-icon>mdi-format-underline</v-icon>
          </v-btn>

        </div>
      </editor-menu-bar>
    </v-row>
    <v-row>
      <v-col cols=12 >
        <editor-content
            class="editor-box"
            :editor="editor"

        />
      </v-col>
    </v-row>
  </v-container>
</template>


<script>
import { Editor, EditorContent, EditorMenuBar  } from 'tiptap';
import { Heading,
  Bold,
  Underline,
    Placeholder
 } from 'tiptap-extensions';
export default {
  components: {
    EditorContent,
    EditorMenuBar,
  },
  data() {
    return {
      editor: null
    }
  },
  mounted() {
    this.editor = new Editor({
      content: this.value,
      onUpdate: ({getHTML}) => {
        this.$emit('input', getHTML())
      },
      extensions:[
        new Heading({levels: [1,2,3]}),
        new Bold(),
        new Underline(),
        new Placeholder({
          emptyEditorClass: 'is-editor-empty',
          emptyNodeClass: 'is-empty',
          emptyNodeText: '글을 작성해주세요.',
          showOnlyWhenEditable: true,
          showOnlyCurrent: true,
        }),
      ],

    })
    this.editor.setContent(this.value)
  },
  props: ["value"],
  beforeDestroy() {
    this.editor.destroy()
  },
  watch: {
    value (val) {
      if(this.editor) this.editor.setContent(val)
    }
  }
};
</script>
<style lang="scss">
.editor-box> * {
  border-color: grey;
  border-style: solid;
  border-width: 1px;
  min-height: 300px;
  text-align: left;
  padding: 10px;
}

.is-active{
  border-color: grey;
  border-style: solid;
  border-width: 1px;
}
/* *:focus {
   outline: none;
}  */
.editor p.is-editor-empty:first-child::before {
  content: attr(data-empty-text);
  float: left;
  color: #aaa;
  pointer-events: none;
  height: 0;
  font-style: italic;
}

</style>