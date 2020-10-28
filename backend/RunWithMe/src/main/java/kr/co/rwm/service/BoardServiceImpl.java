package kr.co.rwm.service;


import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.rwm.entity.Board;
import kr.co.rwm.entity.User;
import kr.co.rwm.repo.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	BoardRepository boardRepository;
	 
	@Override
	public List<Board> allBoardList() {
		return boardRepository.findAll();
	}

	@Override
	public Board save(Map<String, String> boardInfo) {
		
		Board entity = Board.builder()
				.writerId(Integer.parseInt(boardInfo.get("writerId")))
				.boardTitle(boardInfo.get("boardTitle"))
				.boardContent(boardInfo.get("boardContent"))
				.boardRegdate(new Date())
				.boardEditdate(new Date())
				.readCount(0)
				.build();
		
		Board ret = boardRepository.save(entity);
		return ret;
	}

	@Override
	@Transactional
	public Board update(Map<String, String> boardInfo) {
		
		Optional<Board> board = boardRepository.findByBoardId(Integer.parseInt(boardInfo.get("boardId")));
		
		if(!board.isPresent()) {
			return null;
		}
		
		board.get().setBoardContent(boardInfo.get("boardContent"));
		board.get().setBoardEditdate(new Date());
		board.get().setBoardTitle(boardInfo.get("boardTitle"));
		
		return board.get();
	}

	@Override
	public Long delete(int board_id) {
		Long ret = boardRepository.deleteByBoardId(board_id);
		
		return ret;
	}

	@Override
	@Transactional
	public Board detail(int board_id, int uid) {
		
		Optional<Board> board = boardRepository.findByBoardId(board_id);
		if(uid != board.get().getWriterId())
			board.get().setReadCount(board.get().getReadCount()+1);
		System.out.println(uid);
		System.out.println(board.get().getWriterId());
		return board.get();
	}

	@Override
	public Optional<Board> findByBoardId(int board_id) {
		return boardRepository.findByBoardId(board_id);
	}

	@Override
	@Transactional
	public void insertMedia(Optional<Board> board, Board updateBoard) {

		board.ifPresent(selectBoard->{
			selectBoard.setBoardMedia(updateBoard.getBoardMedia());
			boardRepository.save(selectBoard);
		});
	}
	
	
	

}

