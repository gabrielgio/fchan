# Fchan

[![Clojars Project](https://img.shields.io/clojars/v/fchan.svg)](https://clojars.org/fchan)

A simple [4Chan API](https://github.com/4chan/4chan-API) wrapper

This repo is on early stage of development and lacks some features but it is usable.

## Roadmap
* Add lazy-seq support for `get-board-page`.
* Add support for core.async.
* Add more functions to make easier to manege threads/board/posts data.
* 100% test coverage.
## Usage

`fchan` is available as a Maven artifact from [Clojars](https://clojars.org/fchan).

On your Leiningen project file add `[fchan "0.1.2"]`

To get all ids of a board per page:

```Clojure
(get-thread-ids "a")
```

It will return

```Clojure
 [{:page 1,
   :threads [{:no 159627682, :last_modified 1499741301}
             {:no 159515948, :last_modified 1499741300}
             ...]}]
```

To get list of all available boards

```clojure
(get-boards)
```
It will return something like
```Clojure
{:boards [{:cooldowns {:threads 600, :replies 60, :images 60},
           :bump_limit 310,
           :ws_board 1,
           :is_archived 1,
           :max_webm_duration 120,
           :max_comment_chars 2000,
           :title "3DCG",
           :pages 10,
           :meta_description "&quot;/3/ - 3DCG&quot; is 4chan's board for 3D modeling and imagery.",
           :image_limit 150,
           :per_page 15,
           :max_webm_filesize 3145728,
           :max_filesize 4194304,
           :board "3"}
           ...]
:troll_flags {:EU "European", ...}
```

To get page of a board

```Clojure
(get-board-page "a" 1)
```

And it will return 

```Clojure
{:threads [{:posts [{:tn_h 140,
                     :bumplimit 0,
                     :com "Your move, /a/.",
                     :ext ".jpg",
                     :md5 "gy6lPLP+GorNTpYaBJ9oRA==",
                     :tim 1499732135330,
                     :now "07/10/17(Mon)20:15:35",
                     :tn_w 250,
                     :sub "Tenshi no 3P!",
                     :images 32,
                     :semantic_url "tenshi-no-3p",
                     :name "Anonymous",
                     :w 1280,
                     :resto 0,
                     :time 1499732135,
                     :omitted_posts 115,
                     :custom_spoiler 1,
                     :filename "[HorribleSubs] Tenshi no 3P! - 01 [720p].mkv_snapshot_11.06_[2017.07.10_20.43.22]",
                     :fsize 204842,
                     :replies 120,
                     :tail_size 50,
                     :h 720,
                     :no 159641239,
                     :imagelimit 0,
                     :omitted_images 30}
                     ...]}]}
```

To get list of id of all archived thread of a board

```Clojure
(get-archive-threads "a")
```

And it will return an array of thread ids (`:no`)
```Clojure
 [160251728
 160251770
 160251879
 160252141
 ...]
```

To get a list of posts from a thread

```Clojuere
(get-thread "a" 160251728)
```

And it will return a list of posts

```Clojure
{:posts [{:archived 1,
          :tn_h 140,
          :closed 1,
          :bumplimit 0,
          :com "Where were you when this aired?",
          :ext ".png",
          :md5 "ZaLU3hdsXIjr9V7OFMVSsw==",
          :tim 1500941323391,
          :now "07/24/17(Mon)20:08:43",
          :tn_w 250,
          :archived_on 1500950794,
          :sub "The Reflection",
          :images 5,
          :semantic_url "the-reflection",
          :name "Anonymous",
          :w 1920,
          :resto 0,
          :time 1500941323,
          :custom_spoiler 1,
          :filename "01",
          :fsize 1553407,
          :replies 12,
          :h 1080,
          :no 160251728,
          :imagelimit 0}
          ...]}
```

To get a lazy seq a all threads of a post til it reaches end of board

```Clojure
(get-threads "a")

;; to use try something like this
(def threads (get-threads "a"))
(take 1 threads)
``` 
And it will return

```Clojure
({:posts [{:tn_h 250,
           :unique_ips 55,
           :bumplimit 0,
           :com "BD is out. Praise musubi!",
           :ext ".jpg",
           :md5 "JFqXGjP24iIY6i71BR7fXQ==",
           :tim 1501012278567,
           :now "07/25/17(Tue)15:51:18",
           :tn_w 200,
           :sub "Kimi no Na wa. / Your Name.",
           :images 54,
           :semantic_url "kimi-no-na-wa-your-name",
           :name "Anonymous",
           :w 2113,
           :resto 0,
           :time 1501012278,
           :custom_spoiler 1,
           :filename "love umbrella",
           :fsize 321506,
           :replies 187,
           :tail_size 50,
           :h 2638,
           :no 160282736,
           :imagelimit 0}
           ...]})
```

## License

Copyright (c) 2017 Gabriel Giovanini

Distributed under the Eclipse Public License 1.0.
