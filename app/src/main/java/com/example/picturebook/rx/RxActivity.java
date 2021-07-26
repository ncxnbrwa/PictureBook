package com.example.picturebook.rx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import com.example.picturebook.R;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observables.GroupedObservable;
import rx.schedulers.Schedulers;

public class RxActivity extends AppCompatActivity {
    private static final String TAG = "RxActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                /**
                 * 事件队列完结。RxJava不仅把每个事件单独处理，其还会把它们看作一个队列。
                 * 当不会再有新的 onNext发出时，需要触发 onCompleted（）方法作为完成标志。
                 */
                Log.d(TAG, "Subscriber onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {
                /**
                 * 事件队列异常。在事件处理过程中出现异常时，onError（）会被触发
                 * 同时队列自动终止，不允许再有事件发出。
                 */
                Log.d(TAG, "Subscriber onError");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "Subscriber onNext");
            }

            @Override
            public void onStart() {
                /**
                 * 它会在事件还未发送之前被调用，可以用于做一些准备工作
                 * 例如数据的清零或重置。这是一个可选方法，默认情况下它的实现为空。
                 */
                Log.d(TAG, "Subscriber onStart");
            }
        };

        //观察者
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "observer onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {
                Log.d(TAG, "observer onError");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "observer onNext:" + s);
            }
        };

        //被观察者
//        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext("秦始皇");
//                subscriber.onNext("汉高祖");
//                subscriber.onCompleted();
//            }
//        });

        /**
         *这行代码和上面等同
         *会依次调用onNext（"秦始皇"）、onNext（"汉高祖"）、onCompleted（）。
         */
//        Observable observable = Observable.just("秦始皇", "汉高祖");

        //以下代码效果也是一样的
        String[] words = {"秦始皇", "汉高祖"};
        Observable observable = Observable.from(words);

        //订阅操作
//        observable.subscribe(observer);

        //用Action类实现各种调用
        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, "onNextAction call:" + s);
            }
        };
        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.d(TAG, "onErrorAction call:" + throwable.getMessage());
            }
        };
        Action0 onCompleteAction = new Action0() {
            @Override
            public void call() {
                Log.d(TAG, "onCompleteAction call");
            }
        };
        //订阅操作
//        observable.subscribe(onNextAction, onErrorAction, onCompleteAction);

        //============================创建操作符======================================
        //interval相当于定时器
//        Observable.interval(3, TimeUnit.SECONDS)
//                .subscribe(new Action1<Long>() {
//                    @Override
//                    public void call(Long aLong) {
//                        Log.d(TAG, "interval:" + aLong.intValue());
//                    }
//                });

        //输出指定范围内的数据,这里类似for循环操作,range是左闭右开的区间
//        Observable.range(0,5)
//                .subscribe(new Action1<Integer>() {
//                    @Override
//                    public void call(Integer integer) {
//                        Log.d(TAG, "range:" + integer);
//                    }
//                });

        //N次重复发射数据,这里相当于对0~2的数据输出3次
//        Observable.range(0,3)
//                .repeat(3)
//                .subscribe(new Action1<Integer>() {
//                    @Override
//                    public void call(Integer integer) {
//                        Log.d(TAG, "repeat:" + integer);
//                    }
//                });
        //=============================变换操作符=====================================
        /**
         * map操作符通过指定一个Func对象，将Observable转换为一个新的Observable对象并发射
         */
//        final String host = "http://blog.csdn.net/";
//        Observable.just("秦始皇","汉武帝").map(new Func1<String, String>() {
//            @Override
//            public String call(String s) {
//                return host + s;
//            }
//        }).subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                Log.d(TAG, "map:" + s);
//            }
//        });

        /**
         * flatMap操作符将Observable发射的数据集合变换为Observable集合
         * 然后将这些Observable发射的数据平坦化地放进一个单独的 Observable
         * cast 操作符的作用是强制将 Observable 发射的所有数据转换为指定类型。
         */
//        final String host = "http://blog.csdn.net/";
//        List<String> list = new ArrayList<>();
//        list.add("秦始皇");
//        list.add("汉武帝");
//        list.add("唐太宗");
//        list.add("明太祖");
//        Observable.from(list).flatMap(new Func1<String, Observable<?>>() {
//            @Override
//            public Observable<?> call(String s) {
//                return Observable.just(host + s);
//            }
//        }).cast(String.class).subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                Log.d(TAG, "flatMap:" + s);
//            }
//        });

        //flatMapIterable操作符可以将数据包装成Iterable,任何实现了Iterable的容器都可以往下操作数据
//        Observable.just(1, 2, 3).flatMapIterable(new Func1<Integer, Iterable<Integer>>() {
//            @Override
//            public Iterable<Integer> call(Integer integer) {
//                List<Integer> list = new ArrayList<>();
//                list.add(integer + 1);
//                return list;
//            }
//        }).subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                Log.d(TAG, "flatMapIterable:" + integer);
//            }
//        });

        /**
         * buffer操作符将源Observable变换为一个新的Observable
         * 这个新的Observable每次发射一组列表值而不是一个一个发射。
         */
//        Observable.just(1,2,3,4,5,6).buffer(3)
//                .subscribe(new Action1<List<Integer>>() {
//                    @Override
//                    public void call(List<Integer> integers) {
//                        Log.d(TAG, "-----------------");
//                        for (Integer i : integers) {
//                            Log.d(TAG, "buffer:" + i);
//                        }
//                    }
//                });

        /**
         * groupBy操作符用于分组元素，将源Observable变换成一个发射Observables的新Observable （分组后的）。
         */
//        Emperor e1 = new Emperor("唐朝", "唐太宗");
//        Emperor e2 = new Emperor("唐朝", "唐高宗");
//        Emperor e3 = new Emperor("汉朝", "汉武帝");
//        Emperor e4 = new Emperor("秦朝", "秦始皇");
//        Emperor e5 = new Emperor("汉朝", "汉高祖");
//        Emperor e6 = new Emperor("唐朝", "唐宣宗");
//        Observable<GroupedObservable<String, Emperor>> groupBy
//                = Observable.just(e1, e2, e3, e4, e5, e6).groupBy(new Func1<Emperor, String>() {
//            @Override
//            public String call(Emperor emperor) {
//                return emperor.getDynasty();
//            }
//        });
//        Observable.concat(groupBy).subscribe(new Action1<Emperor>() {
//            @Override
//            public void call(Emperor emperor) {
//                Log.d(TAG, "groupBy:" + emperor.getName() + ":"
//                        + emperor.getDynasty());
//            }
//        });

        //==============================过滤操作符==============================
        /**
         * filter操作符是对源Observable产生的结果自定义规则进行过滤
         * 只有满足条件的结果才会提交给订阅者
         */
//        Observable.just(1, 2, 3, 4).filter(new Func1<Integer, Boolean>() {
//            @Override
//            public Boolean call(Integer integer) {
//                return integer > 2;
//            }
//        }).subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                Log.d(TAG, "filter:" + integer);
//            }
//        });

        /**
         * elementAt操作符用来返回指定位置的数据。
         */
//        Observable.just(1, 2, 3, 4).elementAt(2)
//                .subscribe(new Action1<Integer>() {
//                    @Override
//                    public void call(Integer integer) {
//                        Log.d(TAG, "elementAt:" + integer);
//                    }
//                });

        /**
         * distinct 操作符用来去重，其只允许还没有发射过的数据项通过。
         */
//        Observable.just(1,2,2,3,4,4,5,1).distinct()
//                .subscribe(new Action1<Integer>() {
//                    @Override
//                    public void call(Integer integer) {
//                        Log.d(TAG, "elementAt:" + integer);
//                    }
//                });

        /**
         * skip操作符将源Observable发射的数据过滤掉前n项；而take操作符则只取前n项
         * 另外还有skipLast和takeLast操作符，则是从后面进行过滤操作。
         */
//        Observable.just(1,2,3,4,5,6).skip(2).take(2).subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                Log.d(TAG, "skip then take:" + integer);
//            }
//        });

        /**
         * ignoreElements操作符忽略所有源Observable产生的结果
         * 只把Observable的onCompleted和onError事件通知给订阅者
         * 下面的代码onNext不会有打印
         */
//        Observable.just(1,2,3,4).ignoreElements()
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onCompleted() {
//                        Log.d(TAG, "ignoreElements,onCompleted");
//                    }
//
//                    @Override
//                    public void onError(Throwable throwable) {
//                        Log.d(TAG, "ignoreElements,onError");
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        Log.d(TAG, "ignoreElements,onNext:" + integer);
//                    }
//                });

        /**
         * throttleFirst操作符则会定期发射这个时间段里源Observable发射的第一个数据
         * throttleFirst操作符默认在computation 调度器上执行
         */
//        Observable.create(new Observable.OnSubscribe<Integer>() {
//            @Override
//            public void call(Subscriber<? super Integer> subscriber) {
//                for (int i = 0; i < 10; i++) {
//                    subscriber.onNext(i);
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                subscriber.onCompleted();
//            }
//        }).throttleFirst(200, TimeUnit.MILLISECONDS)
//                .subscribe(new Action1<Integer>() {
//                    @Override
//                    public void call(Integer integer) {
//                        Log.d(TAG, "throttleFirst:" + integer);
//                    }
//                });

        /**
         * 如果在设定好的时间结束前源Observable有新的数据发射出来，这个数据就会被丢弃
         * 同时throttleWithTimeOut重新开始计时。
         */
//        Observable.create(new Observable.OnSubscribe<Integer>() {
//            @Override
//            public void call(Subscriber<? super Integer> subscriber) {
//                for (int i = 0; i < 10; i++) {
//                    subscriber.onNext(i);
//                    int sleep = 100;
//                    if (i % 2 == 0) {
//                        sleep = 300;
//                    }
//                    try {
//                        Thread.sleep(sleep);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                subscriber.onCompleted();
//            }
//        }).throttleWithTimeout(200, TimeUnit.MILLISECONDS)
//                .subscribe(new Action1<Integer>() {
//                    @Override
//                    public void call(Integer integer) {
//                        Log.d(TAG, "throttleWithTimeout:" + integer);
//                    }
//                });

        //=========================组合操作符=================================
        /**
         * startWith操作符会在源Observable发射的数据前面插上一些数据
         */
//        Observable.just(4,5,6).startWith(1,2)
//                .subscribe(new Action1<Integer>() {
//                    @Override
//                    public void call(Integer integer) {
//                        Log.d(TAG, "startWith:" + integer);
//                    }
//                });

        /**
         * merge操作符将多个Observable合并到一个Observable中进行发射
         */
//        Observable<Integer> obs1 = Observable.just(1, 2, 3).subscribeOn(Schedulers.io());
//        Observable<Integer> obs2 = Observable.just(4, 5, 6);
//        Observable.merge(obs2, obs1).subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                Log.d(TAG, "merge:" + integer);
//            }
//        });

        /**
         * 将多个 Observable 发射的数据进行合并发射。concat 严格按照顺序发射数据
         * 前一个Observable没发射完成是不会发射后一个Observable的数据的。
         */
//        Observable<Integer> obs1 = Observable.just(1, 2, 3).subscribeOn(Schedulers.io());
//        Observable<Integer> obs2 = Observable.just(4, 5, 6);
//        Observable.concat(obs1, obs2).subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                Log.d(TAG, "merge:" + integer);
//            }
//        });

        /**
         * zip操作符合并两个或者多个Observable发射出的数据项，根据指定的函数变换它们，并发射一个新值。
         */
//        Observable<Integer> obs1 = Observable.just(1, 2, 3);
//        Observable<String> obs2 = Observable.just("a", "b", "c");
//        //泛型类型依次为组合的两个数据类型,最后一个为组合后生成的数据类型
//        Observable.zip(obs1, obs2, new Func2<Integer, String, String>() {
//            @Override
//            public String call(Integer integer, String s) {
//                return integer + s;
//            }
//        }).subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                Log.d(TAG, "zip:" + s);
//            }
//        });

        /**
         * combineLatest和zip效果类似,不过它只会把前一个被观察者的数据和第二个被观察者的数据组合
         */
//        Observable<Integer> obs1 = Observable.just(1, 2, 3, 6);
//        Observable<String> obs2 = Observable.just("a", "b", "c");
//        //泛型类型依次为组合的两个数据类型,最后一个为组合后生成的数据类型
//        Observable.combineLatest(obs2, obs1, new Func2<String, Integer, String>() {
//            @Override
//            public String call(String s, Integer i) {
//                return s + i;
//            }
//        }).subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                Log.d(TAG, "combineLatest:" + s);
//            }
//        });

        //==============================辅助操作符====================================
        //delay操作符让原始Observable在发射每项数据之前都暂停一段指定的时间段。
//        Observable.create(new Observable.OnSubscribe<Long>() {
//            @Override
//            public void call(Subscriber<? super Long> subscriber) {
//                Long currentTime = System.currentTimeMillis() / 1000;
//                subscriber.onNext(currentTime);
//            }
//        }).delay(2, TimeUnit.SECONDS).subscribe(new Action1<Long>() {
//            @Override
//            public void call(Long aLong) {
//                Log.d(TAG, "delay:" + (System.currentTimeMillis() / 1000 - aLong));
//            }
//        });

        /**
         * Do系列操作符就是为原始Observable的生命周期事件注册一个回调
         * 当Observable的某个事件发生时就会调用这些回调。
         */
//        Observable.just(1, 2)
//                .doOnNext(new Action1<Integer>() {
//                    @Override
//                    public void call(Integer integer) {
//                        Log.d(TAG, "call:" + integer);
//                    }
//                }).subscribe(new Subscriber<Integer>() {
//            @Override
//            public void onCompleted() {
//                Log.d(TAG, "onCompleted");
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//                Log.d(TAG, "onError");
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//                Log.d(TAG, "onNext:" + integer);
//            }
//        });

        /**
         * subscribeOn操作符用于指定Observable自身在哪个线程上运行
         * 如果Observable需要执行耗时操作，一般可以让其在新开的一个子线程上运行。
         */
        Observable<Integer> obs = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                Log.d(TAG, "Observable current thread name:" + Thread.currentThread().getName());
                subscriber.onNext(1);
                subscriber.onCompleted();
            }
        });
        //这里被观察者运行在子线程,观察者运行在主线程
        obs.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.d(TAG, "Observer current thread name:" + Thread.currentThread().getName());
                    }
                });

        /**
         * 如果原始 Observable 过了指定的一段时长没有发射任何数据
         * timeout 操作符会以一个onError通知终止这个Observable
         * 或者继续执行一个备用的Observable
         */
//        Observable<Integer> obs = Observable.create(new Observable.OnSubscribe<Integer>() {
//            @Override
//            public void call(Subscriber<? super Integer> subscriber) {
//                for (int i = 0; i < 4; i++) {
//                    try {
//                        Thread.sleep(i * 100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    subscriber.onNext(i);
//                }
//                subscriber.onCompleted();
//            }
//        }).timeout(200, TimeUnit.MILLISECONDS, Observable.just(11, 22));
//        obs.subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                Log.d(TAG, "timeout:" + integer);
//            }
//        });

        //=============================错误处理操作符==================================
        /**
         * onErrorReturn：Observable遇到错误时返回原有Observable行为的备用Observable
         * 备用Observable会忽略原有Observable的onError调用，不会将错误传递给观察者
         * 作为替代，它会发射一个特殊的项并调用观察者的onCompleted方法。
         */
//        Observable.create(new Observable.OnSubscribe<Integer>() {
//            @Override
//            public void call(Subscriber<? super Integer> subscriber) {
//                for (int i = 0; i < 5; i++) {
//                    if (i > 2) {
//                        //模拟抛出一个异常
//                        subscriber.onError(new Throwable("Throwable"));
//                    }
//                    subscriber.onNext(i);
//                }
//                subscriber.onCompleted();
//            }
//        }).onErrorReturn(new Func1<Throwable, Integer>() {
//            @Override
//            public Integer call(Throwable throwable) {
//                return 6;
//            }
//        }).subscribe(new Observer<Integer>() {
//            @Override
//            public void onCompleted() {
//                Log.d(TAG, "onCompleted");
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//                Log.d(TAG, "onError:" + throwable.getMessage());
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//                Log.d(TAG, "onNext:" + integer);
//            }
//        });

        /**
         * onErrorResumeNext：Observable遇到错误时返回原有Observable行为的备用Observable
         * 备用Observable会忽略原有Observable的onError调用，不会将错误传递给观察者
         * 作为替代，它会发射备用Observable的数据。
         */
//        Observable.create(new Observable.OnSubscribe<Integer>() {
//            @Override
//            public void call(Subscriber<? super Integer> subscriber) {
//                for (int i = 0; i < 5; i++) {
//                    if (i > 2) {
//                        //模拟抛出一个异常
//                        subscriber.onError(new Throwable("Throwable"));
//                    }
//                    subscriber.onNext(i);
//                }
//                subscriber.onCompleted();
//            }
//        }).onErrorResumeNext(Observable.just(66, 77, 88))
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onCompleted() {
//                        Log.d(TAG, "onCompleted");
//                    }
//
//                    @Override
//                    public void onError(Throwable throwable) {
//                        Log.d(TAG, "onError:" + throwable.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        Log.d(TAG, "onNext:" + integer);
//                    }
//                });

        /**
         * onExceptionResumeNext：它和onErrorResumeNext类似
         * 不同的是，如果onError收到的Throwable不是一个Exception
         * 它会将错误传递给观察者的onError方法，不会使用备用的Observable。
         */
//        Observable.create(new Observable.OnSubscribe<Integer>() {
//            @Override
//            public void call(Subscriber<? super Integer> subscriber) {
//                for (int i = 0; i < 5; i++) {
//                    if (i > 2) {
//                        //模拟抛出一个异常
////                        subscriber.onError(new Throwable("Throwable"));
//                        subscriber.onError(new Exception("Exception"));
//                    }
//                    subscriber.onNext(i);
//                }
//                subscriber.onCompleted();
//            }
//        }).onExceptionResumeNext(Observable.just(33, 44))
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onCompleted() {
//                        Log.d(TAG, "onCompleted");
//                    }
//
//                    @Override
//                    public void onError(Throwable throwable) {
//                        Log.d(TAG, "onError:" + throwable.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        Log.d(TAG, "onNext:" + integer);
//                    }
//                });

        /**
         * retry操作符不会将原始Observable的onError通知传递给观察者，它会订阅这个Observable
         * 再给它N次机会无错误地完成其数据序列
         * 感觉就是给定重试次数
         */
//        Observable.create(new Observable.OnSubscribe<Integer>() {
//            @Override
//            public void call(Subscriber<? super Integer> subscriber) {
//                for (int i = 0; i < 5; i++) {
//                    if (i == 2) {
//                        //模拟抛出一个异常
//                        subscriber.onError(new Throwable("Throwable"));
//                    }
//                    subscriber.onNext(i);
//                }
//                subscriber.onCompleted();
//            }
//        }).retry(2).subscribe(new Observer<Integer>() {
//            @Override
//            public void onCompleted() {
//                Log.d(TAG, "onCompleted");
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//                Log.d(TAG, "onError:" + throwable.getMessage());
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//                Log.d(TAG, "onNext:" + integer);
//            }
//        });

        //=============================布尔操作符=================================
        /**
         * all操作符根据一个函数对源Observable发射的所有数据进行判断，最终返回的结果就是这个判断结果
         * 这个函数使用发射的数据作为参数,内部判断所有的数据是否满足我们定义好的判断条件
         * 如果全部都满足则返回true，否则就返回false。
         */
//        Observable.just(1, 2, 3, 4, 5)
//                .all(new Func1<Integer, Boolean>() {
//                    @Override
//                    public Boolean call(Integer integer) {
//                        Log.d(TAG, "observable call:" + integer);
//                        return integer < 10;
//                    }
//                }).subscribe(new Action1<Boolean>() {
//            @Override
//            public void call(Boolean aBoolean) {
//                Log.d(TAG, "observer call:" + aBoolean);
//            }
//        });

        /**
         * contains 操作符用来判断源 Observable 所发射的数据是否包含某一个数据
         * 如果包含该数据，会返回true；如果源Observable已经结束了却还没有发射这个数据，则返回false
         * isEmpty操作符用来判断源 Observable 是否发射过数据
         * 如果发射过该数据，就会返回 false；如果源Observable已经结束了却还没有发射这个数据，则返回true。
         */
//        Observable.just(1, 2, 3, 4, 5)
//                .contains(1).subscribe(new Action1<Boolean>() {
//            @Override
//            public void call(Boolean aBoolean) {
//                Log.d(TAG, "contains:" + aBoolean);
//            }
//        });
//
//        Observable.just(1, 2, 3, 4, 5)
//                .isEmpty().subscribe(new Action1<Boolean>() {
//            @Override
//            public void call(Boolean aBoolean) {
//                Log.d(TAG, "isEmpty:" + aBoolean);
//            }
//        });

        //=============================布尔操作符=================================
        /**
         * amb 操作符对于给定两个或多个 Observable
         * 它只发射首先发射数据或通知的那个Observable的所有数据。
         *
         * 这里的代码第一个Observable延时2s发射，所以很显然最终只会发射第二个Observable
         */
//        Observable.amb(Observable.just(1, 2, 3).delay(200, TimeUnit.MILLISECONDS)
//                , Observable.just(4, 5, 6))
//                .subscribe(new Action1<Integer>() {
//                    @Override
//                    public void call(Integer integer) {
//                        Log.d(TAG, "amb:" + integer);
//                    }
//                });

        /**
         * defaultIfEmpty发射来自原始Observable的数据。如果原始Observable没有发射数据，就发射一个默认数据
         */
//        Observable.create(new Observable.OnSubscribe<Integer>() {
//            @Override
//            public void call(Subscriber<? super Integer> subscriber) {
//                subscriber.onCompleted();
//            }
//        }).defaultIfEmpty(666).subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                Log.d(TAG, "defaultIfEmpty:" + integer);
//            }
//        });

        //===========================转换操作符===========================
        /**
         * toList操作符将发射多项数据且为每一项数据调用onNext方法的Observable发射的多项数据组合成一个List
         * 然后调用一次onNext方法传递整个列表。
         */
//        Observable.just(11, 22, 33, 44).toList().subscribe(new Action1<List<Integer>>() {
//            @Override
//            public void call(List<Integer> integers) {
//                for (Integer i : integers) {
//                    Log.d(TAG, "toList:" + i);
//                }
//            }
//        });

        /**
         * toSortedList操作符类似于toList操作符；不同的是，它会对产生的列表排序，默认是自然升序。
         * 但是操作的数据必须实现Comparable接口
         */
//        Observable.just(22,44,11,0).toSortedList().subscribe(new Action1<List<Integer>>() {
//            @Override
//            public void call(List<Integer> integers) {
//                for (Integer i : integers) {
//                    Log.d(TAG, "toSortedList:" + i);
//                }
//            }
//        });

        /**
         * toMap操作符收集原始Observable发射的所有数据项到一个Map（默认是HashMap），然后发射这个Map
         */
//        Emperor e1 = new Emperor("唐朝", "唐太宗");
//        Emperor e2 = new Emperor("汉朝", "汉武帝");
//        Emperor e3 = new Emperor("秦朝", "秦始皇");
//        Observable.just(e1, e2, e3).toMap(new Func1<Emperor, String>() {
//            @Override
//            public String call(Emperor emperor) {
//                //这里确定谁来做key
//                return emperor.getDynasty();
//            }
//        }).subscribe(new Action1<Map<String, Emperor>>() {
//            @Override
//            public void call(Map<String, Emperor> map) {
//                for (Map.Entry<String, Emperor> m : map.entrySet()) {
//                    Log.d(TAG, m.getKey() + "==" + m.getValue());
//                }
//            }
//        });
    }


}
