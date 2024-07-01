package it.unibo.mparty.model.minigames.nanogram.board.impl;

import it.unibo.mparty.model.minigames.nanogram.board.api.SimpleBoard;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.Position;

import java.util.*;
import java.util.stream.IntStream;

public class SimpleBoardImpl extends BoardImpl implements SimpleBoard {

    private final Random random;
    private final int size;

    /**
     *
     * @param size
     * @param fillPercentage a percentage between 0 and 1 that represents filled cells
     */
    public SimpleBoardImpl(final int size, final double fillPercentage) {
        super(size);
        this.random = new Random();
        this.size = size;

        List<Position> position = new ArrayList<>(IntStream.range(0, size * size)
                .mapToObj(i -> new Position(i / size, i % size))
                .toList());
        position.forEach(p -> this.board.put(p, false));
        Collections.shuffle(position);
        position.stream().limit(Math.round(size * size * fillPercentage)).forEach(p -> this.board.replace(p, true));

        System.out.println("SIMPLE BOARD: " + Math.round(size * size * fillPercentage) + "% \n" + board); //todo: remove
    }

    @Override
    public boolean getCell(final int x, final int y) {
        return this.board.get(new Position(x, y));
    }


    public List<Integer> getRowHint(final int row) {
        System.out.println(this.board.entrySet().stream().filter(e -> e.getKey().getX() == row).toList());
        var res = this.board.entrySet().stream().filter(e -> e.getKey().getX() == row)
                .collect(LinkedList<Pair<Boolean, Integer>>::new, (list, entry) -> {
                    if (list.isEmpty() || list.getLast().getX() != entry.getValue()) {
                        list.add(new Pair<>(entry.getValue(), 1));
                    } else {
                        Pair<Boolean, Integer> last = list.pop();
                        list.add(new Pair<>(entry.getValue(), last.getY() + 1));
                    }
                }, (l1, l2) -> {

                });


        var res2 = this.board.entrySet().stream().filter(e -> e.getKey().getX() == row)
                        .map(Map.Entry::getValue).collect(LinkedList<Integer>::new, (l, b) -> {
                            System.out.println("-- "+ b);
                            System.out.println(l);
                            if (l.isEmpty() || !b) {
                                l.add(0);
                            } else {
                                int last = l.pop();
                                l.add(last + 1);
                            }
                }, (l1, l2) -> {
                    if (!l2.isEmpty()) {
                        l1.addAll(l2);
                    }});

        System.out.println(res2);
        return null;
    }


    @Override
    public List<List<Integer>> getHints(final boolean isRow) {
        /*

        00 01 02
        10 11 12
        20 21 22

        true:


        Map<String, List<Student>> studlistGrouped =
        studlist.stream().collect(Collectors.groupingBy(w -> w.stud_location));
         */


        //Map<Integer, List<Map.Entry<Position, Boolean>>> map =

        //{0=[Position[x=0, y=0]=true, Position[x=0, y=1]=false,

        //IntStream.range(1, numberList.size())
        //         .anyMatch(i -> numberList.get(i).equals(numberList.get(i-1)));

        //var map =grid.entrySet().stream()
         //       .collect(Collectors.groupingBy(e -> isRow ? e.getKey().x() : e.getKey().y())); //okk


        //grid.entrySet().stream().collect(LinkedList<List<Integer>>::new, (list, entry) -> {
            //if (list.isEmpty() || !list.getLast().get(0).equals(entry.getKey().getX()))
            //{
            //    list.add(new ArrayList<>());
            //}
            //list.getLast().add(value);
//        }, (l1, l2) -> {
//
//        });

//        Map<Integer, Long> result = grid.entrySet().stream()
//                .collect(Collectors.groupingBy(
//                        e -> isRow ? e.getKey().x() : e.getKey().y(),
//                        Collectors.filtering(
//                                Map.Entry::getValue,
//                                Collectors.counting()
//                        )
//                ));
//
//        List<List<Integer>> resultList = grid.entrySet().stream()
//                .collect(Collectors.groupingByConcurrent(
//                        e -> isRow ? e.getKey().getX() : e.getKey().getY(),
//                        Collectors.summingInt(e -> e.getValue() ? 1 : 0)
//                ))
//                .values().stream()
//                .map(Collections::singletonList)
//                .collect(Collectors.toList());
//
//        System.out.println(map);
//        System.out.println(result);
//        System.out.println(resultList);


        final List<List<Integer>> hintsList = new ArrayList<>();

        for (int i = 0; i < this.size; i++) {
            final List<Integer> hints = new ArrayList<>();
            int count = 0;

            for (int j = 0; j < this.size; j++) {
                final Position pos = isRow ? new Position(i, j) : new Position(j, i);
                final Boolean currentState = this.board.get(pos);

                if (currentState == true) {
                    count++;
                } else if (count > 0) {
                    hints.add(count);
                    count = 0;
                }
            }

            if (count > 0) {
                hints.add(count);
            } else if (hints.isEmpty()) {
                hints.add(0);
            }

            hintsList.add(hints);
        }
        return hintsList;
    }

    private boolean areAdjacent(Position c1, Position c2) {
        return c1.getX() == c2.getX() || c1.getY() == c2.getY();
    }

//        //todo: stream
//        final List<List<Integer>> hintsList = new ArrayList<>();
//
//        for (int i = 0; i < this.size; i++) {
//            final List<Integer> hints = new ArrayList<>();
//            int count = 0;
//
//            for (int j = 0; j < this.size; j++) {
//                final Position pos = isRow ? new Position(i, j) : new Position(j, i);
//                final Boolean currentState = this.grid.get(pos);
//
//                if (currentState == true) {
//                    count++;
//                } else if (count > 0) {
//                    hints.add(count);
//                    count = 0;
//                }
//            }
//
//            if (count > 0) {
//                hints.add(count);
//            } else if (hints.isEmpty()) {
//                hints.add(0);
//            }
//
//            hintsList.add(hints);
//        }
//        return hintsList;
//    }


    @Override
    public String toString() {
        return "SimpleBoardImpl{" +
                "board=" + board +
                '}';
    }
}
