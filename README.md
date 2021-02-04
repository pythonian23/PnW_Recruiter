# Introduction

This is a simple Politics & War recruiter, with what I believe to be minimal setup.

# Setup

You will need to locate the working directory of the program, which is usually the same folder as the program. You will need to add 3 files:

## Files

- *The file names are **case-sensitive**. Make sure you name them correctly!*

### `savedData.txt`

```
API_KEY
LATEST_NATION
```

- This file contains your API key and the latest nation that you sent the recruitment message to.
- API_KEY should be your API key
- LATEST_NATION will update itself when you run the program, but you will need to set an initial value. Select a [recently created nation](https://politicsandwar.com/nations/) and copy its numerical ID. If it isn't a new nation, you could end up messaging hundreds of inactive nations on your first run.
- You may also have to update LATEST_NATION manually if you haven't run the program in a while.

### `title.txt`

```
TITLE_1
TITLE_2
TITLE_3
```
- You may only have just one title, or multiple to (pseudo-)randomly choose from.
- Titles should be separated with a newline.
- *Do not leave a blank line at the end of the file, or the program will not run!*

### `message.html`

- This is the file that contains the body of your recruitment message.
- It may be in plain text or in HTML format.
- You may need to do some testing to see which parts of HTML work. Some things, such as background images, seem to not work.
- *It seems that the server cannot process unicode or HTML entities very well (or it could be that I'm doing something wrong). Do not use these in the file, or your message will be cut off in the middle.*
- I am planning to add a feature to replace certain keywords with the name of the send target. It may or may not be implemented in the future.
